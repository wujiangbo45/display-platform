package com.mapbar.common.exception.handler;

import com.mapbar.common.exception.http.LocalCloudException;
import com.mapbar.common.exception.http.LocalCloudParamErrorException;
import com.mapbar.common.exception.http.LocalCloudRefusedException;
import com.mapbar.common.exception.http.LocalCloudServerErrorException;
import com.mapbar.common.web.bind.GenericResponse;
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
