package com.mapbar.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 14:09
 */
@Component
public class UrlProperties {

    public static Properties props;

    @Value("${location.service.address}")
    public String locationServiceUrl;

    @Autowired
    private void init(){
        Resource resourceSql = new ClassPathResource("url.properties");
        InputStream is = null;
        props = new Properties();
        try {
            is = resourceSql.getInputStream();
            props.clear();
            props.load(is);
            Set<Map.Entry<Object, Object>> entrySet =  props.entrySet();
            for (Map.Entry<Object,Object> entry : entrySet) {
                props.setProperty(String.valueOf(entry.getKey()),locationServiceUrl + String.valueOf(entry.getValue()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getUrl(String key){
        return String.valueOf(props.get(key));
    }

}
