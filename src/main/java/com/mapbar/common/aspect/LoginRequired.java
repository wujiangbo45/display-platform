package com.mapbar.common.aspect;

import java.lang.annotation.*;

/**
 * 身份验证注解，进行token验证，token默认的key为token，请求时需要携带token进行验证
 * @Author: wujiangbo
 * @Create: 2017/05/24 10:59
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
    String value() default "token";
}