package com.mapbar.tds.common;

import com.mapbar.tds.result.GenericResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/18 14:24
 */

public class GenericResponseBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

    private static final String REQ_ID_PARAM_NAME = "method";


    public GenericResponseBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return false;
    };

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return ((AnnotationUtils.findAnnotation(returnType.getContainingClass(), GenericResponseBody.class) != null) ||
                (returnType.getMethodAnnotation(GenericResponseBody.class) != null));
    }
    @Override
    public void handleReturnValue(Object returnValue,
                                  MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws HttpMediaTypeNotAcceptableException, IOException {

        /**包装方法返回的bean*/
        GenericResponse<Object> wrapper = new GenericResponse<Object>(returnValue);
        super.handleReturnValue(wrapper, returnType, mavContainer, webRequest);
    }

}
