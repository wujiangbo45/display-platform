package com.mapbar.display.aspect;

import com.mapbar.display.dto.TokenReq;
import com.mapbar.display.exception.business.TokenExpireException;
import com.mapbar.display.util.RedisUtil;


import com.mapbar.display.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 11:00
 */
@Aspect
@Component
public class LoginRequiredSupoort {
    private final Logger logger = LoggerFactory.getLogger(LoginRequiredSupoort.class);
    @Autowired
    RedisUtil redisUtil;

    @Pointcut("@annotation(com.mapbar.display.aspect.LoginRequired)")
    @Order(1)
    public void loginRequiredAspect() {
    }

    @Around("loginRequiredAspect()")
    public Object loginRequiredSupport(ProceedingJoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();


        // 获取登录信息
        Object obj = null;
        TokenReq tokenReq = (TokenReq)joinPoint.getArgs()[0];
        String token = tokenReq.getToken();
        String stoken = "stoken_" + token;
        // 记录下请求内容
        logger.info("=======================token验证开始==========================");
        logger.info("====  URL : " + request.getRequestURL().toString());
        logger.info("====  TOKEN : " + token);
        // 判断缓存里是否有token,并且key的值不为空
        if(redisUtil.hasKey(stoken) && StringUtil.isNotEmpty(redisUtil.get(stoken))){
            logger.info("====  SUCCESS : token验证成功");
            logger.info("=======================token验证结束==========================");
            obj = joinPoint.proceed();
        }else {
            // token expire
            logger.error("====  FAILURE : token验证失败");
            logger.info("=======================token验证结束==========================");
            throw new TokenExpireException();
        }
        return obj;
    }

}
