package com.mapbar.platform.common.exception.handler;

import com.mapbar.platform.common.exception.http.*;
import com.mapbar.platform.common.web.bind.GenericResponse;
import org.springframework.context.MessageSource;

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
