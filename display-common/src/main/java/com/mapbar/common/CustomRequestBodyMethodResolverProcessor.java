package com.navinfo.opentsp.qingqi.common.util;

import com.navinfo.opentsp.qingqi.common.command.BaseCommand;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 重写参数处理类,处理标注@RequestBody参数的
 * @author: wujiangbo
 * @Create: 2017/09/21 上午10:00
 */
@Component
public class CustomRequestBodyMethodResolverProcessor extends RequestResponseBodyMethodProcessor {

    public CustomRequestBodyMethodResolverProcessor(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return super.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }

    /**
     * 重写 {@link ServletServerHttpRequest}中的getBody()方法
     * @param webRequest
     * @param parameter
     * @param paramType
     * @return
     * @throws IOException
     * @throws HttpMediaTypeNotSupportedException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter parameter,
                                                   Type paramType) throws IOException, HttpMediaTypeNotSupportedException, HttpMessageNotReadableException {

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        CustomServletServerRequest customServletServerRequest = new CustomServletServerRequest(servletRequest);
        Object arg = readWithMessageConverters(customServletServerRequest, parameter, paramType);
        if (arg == null) {
            if (checkRequired(parameter)) {
                throw new HttpMessageNotReadableException("Required request body is missing: " +
                        parameter.getMethod().toGenericString());
            }
        }
        return arg;
    }

    @Override
    protected Object adaptArgumentIfNecessary(Object arg, MethodParameter parameter) {
        // BaseCommand需要设置页数-1
        if (arg instanceof BaseCommand){
            BaseCommand command = (BaseCommand)arg;
            command.setPage_number(String.valueOf(Integer.parseInt(command.getPage_number()) - 1));
            arg = command;
        }
        return super.adaptArgumentIfNecessary(arg, parameter);
    }
}
