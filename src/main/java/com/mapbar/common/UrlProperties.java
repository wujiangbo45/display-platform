package com.mapbar.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 14:09
 */
@Component
public class UrlProperties {

    public static Properties props;

    @Autowired
    private void init(){
        Resource resourceSql = new ClassPathResource("url.properties");
        InputStream is = null;
        props = new Properties();
        try {
            is = resourceSql.getInputStream();
            props.clear();
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getUrl(String key){
        return String.valueOf(props.get(key));
    }

}
