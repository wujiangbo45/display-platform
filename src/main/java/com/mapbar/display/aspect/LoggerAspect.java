package com.mapbar.display.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/23 11:24
 */
@Aspect
@Component
public class LoggerAspect {

    private Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.mapbar.display.controller..*(..))")
    @Order(0)
    public void controllerLogger(){

    }

    @Pointcut("execution(* com.mapbar.display.service..*(..))")
    @Order(2)
    public void serviceLogger(){

    }


    /**
     * controller log切面处理
     * @param joinPoint
     */
    @Before("controllerLogger()")
    public void beforeLogger(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("=======================请求处理开始==========================");
        logger.info("====  URL : " + request.getRequestURL().toString());
        logger.info("====  HTTP_METHOD : " + request.getMethod());
        logger.info("====  IP : " + request.getRemoteAddr());
        logger.info("====  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("====  参数 : " + Arrays.toString(joinPoint.getArgs()));
    }

    /**
     *
     * @param joinPoint
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "controllerLogger()")
    public void doAfterReturning(JoinPoint joinPoint,Object ret) throws Throwable {
        // 处理完请求，返回内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("=======================RESPONSE INFO=======================" );
        logger.info("====  REQUEST URL : " + request.getRequestURL());
        logger.info("====  RESPONSE : " + ret.toString());
        logger.info("====  SPEND TIME : " + (System.currentTimeMillis() - startTime.get()) + "ms");
        logger.info("=======================请求处理结束==========================");
    }


    @AfterThrowing(throwing = "ex",pointcut = "controllerLogger()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("=======================THROWS INFO================================" );
        logger.info("====  REQUEST URL : " + request.getRequestURL());
        logger.info("====  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("====  THROW EXCEPTION : " + ex.getMessage());
        logger.info("=======================请求处理结束,出现异常==========================");
    }


    @Before("serviceLogger()")
    public void beforeServiceLogger(JoinPoint joinPoint){
        // 记录下请求内容
        logger.info("=======================service处理开始==========================");
        logger.info("====  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("====  参数 : " + Arrays.toString(joinPoint.getArgs()));
    }


    @AfterReturning(returning = "ret", pointcut = "serviceLogger()")
    public void doAfterServiceReturning(JoinPoint joinPoint, Object ret) throws Throwable {
        logger.info("=======================service返回信息===========================" );
        logger.info("====  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("====  RESPONSE : " + ret.toString());
        logger.info("=======================service处理结束===========================");
    }

    @AfterThrowing(throwing = "ex",pointcut = "serviceLogger()")
    public void doAfterServiceThrowing(JoinPoint joinPoint, Throwable ex){

        logger.info("=======================service THROWS INFO=========================" );
        logger.info("====  CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("====  THROW EXCEPTION : " + ex.getMessage());
        logger.info("=======================service结束,出现异常==========================");
    }

}
