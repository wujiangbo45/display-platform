package com.mapbar.display.service;

import com.mapbar.display.command.LoginInReq;
import com.mapbar.display.common.CommonDao;
import com.mapbar.display.common.Const;
import com.mapbar.display.dto.HyAccountResp;
import com.mapbar.display.exception.ErrorCode;
import com.mapbar.display.exception.business.UserCheckException;
import com.mapbar.display.service.base.BaseService;
import com.mapbar.display.util.JsonUtil;
import com.mapbar.display.util.MD5Util;
import com.mapbar.display.util.RedisUtil;
import com.mapbar.display.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by suixin on 2017/5/19.
 */
@Service
public class LoginService extends BaseService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    public CommonDao dao;

    String accountPwd = null;

    /**
     * 登陆
     *
     * @param command
     * @return
     */
    @Transactional
    public HyAccountResp login(LoginInReq command) throws Exception{
        // 查询用户信息
        HyAccountResp hyAccountDto = (HyAccountResp)dao.sqlFindObject("queryLoginInfoSql", command, HyAccountResp.class);
        if (null != hyAccountDto && command.getUsername().equals(hyAccountDto.getAccountname())) {
            accountPwd = hyAccountDto.getAccountpwd();
            // 密码md5加密校验
            MD5Util md5Util = new MD5Util();
            if (md5Util.checkPasswordAndUserPwd(command.getPassword(), accountPwd)) {
                // 生成token
                String token = StringUtil.getUUID();
                hyAccountDto.setToken(token);

                // json格式转换
                String json = JsonUtil.toJson(hyAccountDto);

                // redis对应的key
                String uKey = Const.USER_KEY_PREFX + command.getUsername();
                String tKey = Const.TOKEN_KEY_PREFX + token;
                // 删除redis记录
                if(redisUtil.hasKey(uKey)){
                    String oldToken = redisUtil.get(uKey);
                    redisUtil.delete(uKey);
                    redisUtil.delete(Const.TOKEN_KEY_PREFX + oldToken);
                }
                // 存入redis
                redisUtil.set(uKey, token, Const.TOKEN_LIVE_TIME_MINUTE);//用户对应的token
                redisUtil.set(tKey, json,Const.TOKEN_LIVE_TIME_MINUTE);//token对应的用户信息

            } else {
                throw new UserCheckException(ErrorCode.ERROR_PASSWD);
            }
        } else {
            throw new UserCheckException(ErrorCode.USER_NOT_FOUND);
        }
        return hyAccountDto;
    }


}
