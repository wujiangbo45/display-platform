package com.mapbar.display.exception.handler;

import com.mapbar.display.exception.ErrorCode;
import com.mapbar.display.exception.business.TokenExpireException;
import com.mapbar.display.exception.business.TokenRequiredException;
import com.mapbar.display.exception.business.UserCheckException;
import com.mapbar.display.exception.http.HttpRequestException;
import com.mapbar.display.exception.http.LocalCloudException;
import com.mapbar.display.common.bind.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/18 15:51
 */
@ControllerAdvice
public class HandlerException {
    private final Logger logger = LoggerFactory.getLogger(HandlerException.class);

    @Autowired
    protected MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public GenericResponse<?> handleBindException(HttpServletRequest request, BindException ex) {
        logger.error("bind exception", ex);
        GenericResponse<?> resp = null;

        String code = ErrorCode.PARAM_ERR;

        resp = new GenericResponse<>(code, ex.getAllErrors().get(0).getDefaultMessage(), null);

        return resp;
    }

    /**
     * 处理未知异常
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public GenericResponse<?> handleThrowable(HttpServletRequest request, Throwable ex) {
        logger.error("Unknown error", ex);
        GenericResponse<?> resp = null;

        String code = ErrorCode.UNKNOWN_ERR;

        resp = new GenericResponse<>(code, "未知错误", null);

        return resp;
    }


    /**
     * 处理http请求异常
     *
     * @param request
     * @param ex
     * @return
     * @author:wujiangbo Created on 2016年3月15日 上午9:35:19
     * @modify author:修改人
     * Modify on 修改时间
     */
    @ExceptionHandler(HttpRequestException.class)
    @ResponseBody
    public GenericResponse<?> handleHttpRequestException(HttpServletRequest request, HttpRequestException ex) {
        logger.error("http request failed", ex);
        GenericResponse<?> resp = null;
        resp = HttpRequestExceptionHandler.handleHttpRequestException(request, ex, messageSource);
        if (resp != null) {
            return resp;
        } else {
            resp = handleThrowable(request, ex);
        }
        return resp;
    }

    @ExceptionHandler(LocalCloudException.class)
    @ResponseBody
    public GenericResponse<?> handleLocalCloudException(HttpServletRequest request, LocalCloudException ex) {
        logger.error("request local cloud failed", ex);
        GenericResponse<?> resp = null;
        resp = LocalCloudExceptionHandler.handleLocalCloudException(request, ex, messageSource);
        if (resp != null) {
            return resp;
        } else {
            resp = handleThrowable(request, ex);
        }
        return resp;
    }


    /**
     * 处理servlet异常
     *
     * @param request
     * @param ex
     * @return
     * @author:wujiangbo Created on 2016年3月15日 下午2:20:55
     * @modify author:修改人
     * Modify on 修改时间
     */
    @ExceptionHandler(ServletException.class)
    @ResponseBody
    public GenericResponse<?> handleServletException(HttpServletRequest request, ServletException ex) {
        logger.error("Servlet exception", ex);

        GenericResponse<?> resp = ServletExceptionHandler.handleHttpRequestException(request, ex, messageSource);
        if (resp != null) {
            return resp;
        } else {
            resp = handleThrowable(request, ex);
        }
        return resp;
    }
    @ExceptionHandler(UserCheckException.class)
    @ResponseBody
    public GenericResponse<?> handleUserCheckException(HttpServletRequest request, UserCheckException ex){
        logger.error("UserCheckException exception", ex);
        String code = ex.getCode();
        String msg = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        return new GenericResponse<>(code,msg,null);
    }

    @ExceptionHandler(TokenExpireException.class)
    @ResponseBody
    public GenericResponse<?> handleTokenExpireException(HttpServletRequest request, TokenExpireException ex){
        logger.error("TokenExpireException exception", ex);
        String code = ex.getCode();
        String msg = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        return new GenericResponse<>(code,msg,null);
    }

    @ExceptionHandler(TokenRequiredException.class)
    @ResponseBody
    public GenericResponse<?> handleTokenRequiredException(HttpServletRequest request, TokenRequiredException ex){
        logger.error("TokenRequiredException exception", ex);
        String code = ex.getCode();
        String msg = messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
        return new GenericResponse<>(code,msg,null);
    }



}
