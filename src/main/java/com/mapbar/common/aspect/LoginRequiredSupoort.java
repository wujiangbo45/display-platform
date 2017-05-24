package com.mapbar.common.aspect;

import com.mapbar.common.Const;
import com.mapbar.common.exception.business.TokenExpireException;
import com.mapbar.common.exception.business.TokenRequiredException;
import com.mapbar.common.utils.RedisUtil;


import com.mapbar.common.utils.StringUtil;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Pointcut("@annotation(com.mapbar.common.aspect.LoginRequired)")
    @Order(1)
    public void loginRequiredAspect() {
    }

    @Around("loginRequiredAspect()")
    public Object loginRequiredSupport(ProceedingJoinPoint joinPoint) throws Throwable {

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Method method = getMethod(joinPoint);

        LoginRequired anno = (LoginRequired)method.getAnnotation(LoginRequired.class);

        String tokenKey = anno.value();
        String token = request.getParameter(tokenKey);
        // 记录下请求内容
        logger.info("=======================token验证开始==========================");
        logger.info("====  URL : " + request.getRequestURL().toString());
        logger.info("====  TOKEN : " + token);

        // get token from request
        // check null
        if (StringUtil.isEmpty(token)){
            logger.error("====  FAILURE : token验证失败:无token参数");
            logger.info("=======================token验证结束==========================");
            throw new TokenRequiredException(tokenKey);
        }

        // 获取登录信息
        Object obj = null;
        String stoken = Const.TOKEN_KEY_PREFX + token;

        // 判断缓存里是否有token,并且key的值不为空
        if(redisUtil.hasKey(stoken) && StringUtil.isNotEmpty(redisUtil.get(stoken))){
            logger.info("====  SUCCESS : token验证成功");
            logger.info("=======================token验证结束==========================");
            obj = joinPoint.proceed();
        }else {
            // token expire
            logger.error("====  FAILURE : token验证失败:token失效");
            logger.info("=======================token验证结束==========================");
            throw new TokenExpireException(tokenKey);
        }
        return obj;
    }



    public Method getMethod(final ProceedingJoinPoint pjp) {
        // 获取参数的类型
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[pjp.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
        } catch (NoSuchMethodException e) {
            method = findMethod(pjp.getTarget().getClass(), pjp.getSignature().getName(), argTypes);
            if (method == null) {
                //logger.error("CacheAspect cannot get method for " + pjp.getSignature().getName(), e);
                logger.error("(获取被拦截方法对象getMethod)缓存切面不能获取名为:【" + pjp.getSignature().getName()+ "】的方法! ", e);
            }
        } catch (SecurityException e) {
            //logger.error("CacheAspect cannot get method with expect annotation", e);
            logger.error("(获取被拦截方法对象getMethod)缓存切面不能获取到期望标注的方法! ", e);
        }
        return method;

    }

    /**
     * 获取method信息
     *
     * @param clazz
     * @param methodName
     * @param paramsTypes
     * @return
     * @author:武江波 Created on 2016年4月19日 下午3:23:14
     * @modify author:修改人 Modify on 修改时间
     */
    public static Method findMethod(final Class<?> clazz, final String methodName, final Class<?>[] paramsTypes) {
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> sameMethods = new ArrayList<Method>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                sameMethods.add(method);
            }
        }
        if (sameMethods.isEmpty()) {
            return null;
        }
        List<Method> sameCountParams = new ArrayList<Method>();
        for (Method method : sameMethods) {
            if (method.getParameterTypes().length == paramsTypes.length) {
                sameCountParams.add(method);
            }
        }
        if (sameCountParams.isEmpty()) {
            return null;
        }
        for (Method method : sameCountParams) {
            Class<?>[] params = method.getParameterTypes();
            boolean good = true;
            for (int i = 0; i < params.length && good; i++) {
                if (params[i].isAssignableFrom(paramsTypes[i])) {
                    good = true;
                    continue;
                }
                if (params[i].isInterface() && Arrays.asList(paramsTypes[i].getInterfaces()).contains(params[i])) {
                    good = true;
                    continue;
                } else {
                    if (paramsTypes[i].getSuperclass().equals(params[i])) {
                        good = true;
                        continue;
                    }
                }
                good = false;
            }
            if (good) {
                return method;
            }
        }
        return null;
    }
}