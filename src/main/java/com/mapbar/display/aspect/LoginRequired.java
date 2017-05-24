package com.mapbar.display.aspect;

import java.lang.annotation.*;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 10:59
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {
    boolean value() default true;
}