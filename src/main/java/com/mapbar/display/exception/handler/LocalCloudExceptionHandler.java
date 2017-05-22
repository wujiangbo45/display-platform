package com.mapbar.display.exception.handler;

import com.mapbar.display.exception.http.*;
import com.mapbar.display.result.GenericResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 11:02
 */
public class LocalCloudExceptionHandler {

    public static GenericResponse<?> handleLocalCloudException(HttpServletRequest request, LocalCloudException ex, MessageSource messageSource) {
        GenericResponse<?> resp = null;

        if (ex instanceof LocalCloudParamErrorException) {
            LocalCloudParamErrorException businessException = (LocalCloudParamErrorException)ex;
            resp = new GenericResponse<>(businessException.getErrCode(),businessException.getReasonPhrase(),null);
        }
        else if (ex instanceof LocalCloudRefusedException) {
            LocalCloudRefusedException businessException = (LocalCloudRefusedException)ex;
            resp = new GenericResponse<>(businessException.getErrCode(),businessException.getReasonPhrase(),null);
        }
        else if (ex instanceof LocalCloudServerErrorException) {
            LocalCloudServerErrorException businessException = (LocalCloudServerErrorException)ex;
            resp = new GenericResponse<>(businessException.getErrCode(),businessException.getReasonPhrase(),null);
        }
        return resp;
    }
}
