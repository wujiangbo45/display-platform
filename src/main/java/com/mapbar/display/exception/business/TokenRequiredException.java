package com.mapbar.display.exception.business;

import com.mapbar.display.exception.ErrorCode;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 12:47
 */
public class TokenRequiredException extends RuntimeException {
    private String code;

    public TokenRequiredException(){
        super();
        this.code = ErrorCode.TOKEN_REQUIRED;
    }

    public String getCode() {
        return code;
    }
}
