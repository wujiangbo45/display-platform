package com.mapbar.display.exception;

import com.mapbar.display.result.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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

        String code = ErrorCode.UNKNOWN_ERR;

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


}
