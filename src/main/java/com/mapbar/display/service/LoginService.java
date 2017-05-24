package com.mapbar.display.service;

import com.mapbar.display.command.LoginInCommand;
import com.mapbar.display.common.CommonDao;
import com.mapbar.display.dto.HyAccountDto;
import com.mapbar.display.result.CommonResult;
import com.mapbar.display.result.ReturnCode;
import com.mapbar.display.util.JsonUtil;
import com.mapbar.display.util.MD5Util;
import com.mapbar.display.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suixin on 2017/5/19.
 */
@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public CommonDao dao;

    String accountPwd = null;

    /**
     * 登陆
     *
     * @param command
     * @return
     */
    public HyAccountDto login(LoginInCommand command) {
        CommonResult result = new CommonResult();
        result.fillResult(ReturnCode.OK);
        HyAccountDto hyAccountDto = new HyAccountDto();
        logger.info("login--------start--------");
        try {
            // 查询用户信息
            List<HyAccountDto> dtoList = dao.sqlFind("queryLoginInfoSql", command, HyAccountDto.class);
            if (null != dtoList && !dtoList.isEmpty() && command.getAccountName().equals(dtoList.get(0).getAccountName())) {
                // 用户信息
                hyAccountDto = dtoList.get(0);

                // 密码md5加密校验
                MD5Util md5Util = new MD5Util();
                if (md5Util.checkPasswordAndUserPwd(command.getAccountPwd(), accountPwd)) {
                    // 生成token
                    String token = StringUtil.getUUID();
                    hyAccountDto.setToken(token);

                    // json格式转换
                    String json = JsonUtil.toJson(hyAccountDto);

                    // redis对应的key
                    String uKey = "user_" + command.getAccountName();
                    String tKey = "stoken_" + token;
                    // 删除redis记录
                    if(redisTemplate.hasKey(uKey)){
                        String oldToken = redisTemplate.boundValueOps(uKey).get();
                        redisTemplate.delete(uKey);
                        redisTemplate.delete("stoken_" + oldToken);
                    }
                    // 存入redis
                    redisTemplate.opsForValue().set(uKey, token);//用户对应的token
                    redisTemplate.opsForValue().set(tKey, json);//token对应的用户信息

                } else {
                    result.fillResult(ReturnCode.CLIENT_ERROR);
                    result.setErrmsg("密码不正确");
                }
            } else {
                result.fillResult(ReturnCode.CLIENT_ERROR);
                result.setMessage("此用户不存在");
            }
        } catch (Exception e) {
            result.fillResult(ReturnCode.SERVER_ERROR);
            logger.error("LOGIN Exception: " + e.getMessage());
        }
        logger.info("====================================login End==================================");
        return hyAccountDto;
    }


}
