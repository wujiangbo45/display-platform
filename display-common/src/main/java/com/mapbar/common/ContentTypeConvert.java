package com.navinfo.opentsp.qingqi.common.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLDecoder;

/**
 * @author: wujiangbo
 * @Create: 2017/09/20 下午1:33
 */
@Component
public class ContentTypeConvert extends AbstractGenericHttpMessageConverter<Object> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final String AND = "&";
    private static final String EQ = "=";
    private static final String PLUS = "+";
    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    @Override
    protected void writeInternal(Object o, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(JsonUtil.toJson(o).getBytes());
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String s = CharStreams.toString(new InputStreamReader(inputMessage.getBody()));
        logger.debug("received body :" + s);
        Object o;
        Class clz;
        try {
            clz = Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        try {
            Assert.notNull(clz,"类不可以为空!");
            o = MAPPER.readValue(s, clz);
        }catch (Exception e){
            String sb = paramJson(s);
            o = JsonUtil.fromJson(URLDecoder.decode(sb,"UTF-8"),clz);
        }
        return o;
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return mediaType.includes(MediaType.APPLICATION_JSON) ||
                mediaType.includes(MediaType.APPLICATION_FORM_URLENCODED) ||
                mediaType.includes(MediaType.APPLICATION_OCTET_STREAM) ||
                mediaType.includes(MediaType.APPLICATION_JSON_UTF8);
    }


    private String paramJson(String paramIn) {
        StringBuilder sb = new StringBuilder();
        if (paramIn.contains(AND)) {
            sb.append("{");
            String []prm = paramIn.split(AND);
            for (int i = 0; i < prm.length; i++) {
                String repStr = prm[i].replaceFirst(EQ, "\":\"");
                if (repStr.contains("\":\"")){
                    sb.append("\"");
                    if (repStr.contains(PLUS) && repStr.contains("token")){
                        sb.append(repStr.replaceAll("\\+","%2B").replaceFirst(EQ, "\":\""));
                    }else{
                        sb.append(repStr);
                    }

                    sb.append("\"");
                    if (i < prm.length - 1){
                        sb.append(",");
                    }
                }

            }
            sb.append("}");
        } else if (paramIn.contains(EQ)){
                sb.append("{\"");
                if (paramIn.contains(PLUS) && paramIn.contains("token")){
                    sb.append(paramIn.replaceAll("\\+","%2B").replaceFirst(EQ, "\":\""));
                }else{
                    sb.append(paramIn.replaceFirst(EQ, "\":\""));
                }

                sb.append("\"}");
        }else{
            sb.append("{}");
        }

        return sb.toString();
    }

}
