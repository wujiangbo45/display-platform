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
     * ��½
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
            // ��ѯ�û���Ϣ
            List<HyAccountDto> dtoList = dao.sqlFind("queryLoginInfoSql", command, HyAccountDto.class);
            if (null != dtoList && !dtoList.isEmpty() && command.getAccountName().equals(dtoList.get(0).getAccountName())) {
                // �û���Ϣ
                hyAccountDto = dtoList.get(0);

                // ����md5����У��
                MD5Util md5Util = new MD5Util();
                if (md5Util.checkPasswordAndUserPwd(command.getAccountPwd(), accountPwd)) {
                    // ����token
                    String token = StringUtil.getUUID();
                    hyAccountDto.setToken(token);

                    // json��ʽת��
                    String json = JsonUtil.toJson(hyAccountDto);

                    // redis��Ӧ��key
                    String uKey = "user_" + command.getAccountName();
                    String tKey = "stoken_" + token;
                    // ɾ��redis��¼
                    if(redisTemplate.hasKey(uKey)){
                        String oldToken = redisTemplate.boundValueOps(uKey).get();
                        redisTemplate.delete(uKey);
                        redisTemplate.delete("stoken_" + oldToken);
                    }
                    // ����redis
                    redisTemplate.opsForValue().set(uKey, token);//�û���Ӧ��token
                    redisTemplate.opsForValue().set(tKey, json);//token��Ӧ���û���Ϣ

                } else {
                    result.fillResult(ReturnCode.CLIENT_ERROR);
                    result.setErrmsg("���벻��ȷ");
                }
            } else {
                result.fillResult(ReturnCode.CLIENT_ERROR);
                result.setMessage("���û�������");
            }
        } catch (Exception e) {
            result.fillResult(ReturnCode.SERVER_ERROR);
            logger.error("LOGIN Exception: " + e.getMessage());
        }
        logger.info("====================================login End==================================");
        return hyAccountDto;
    }


}
