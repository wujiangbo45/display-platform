package com.mapbar.common.exception.business;


import com.mapbar.common.exception.ErrorCode;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 12:47
 */
public class TokenRequiredException extends RuntimeException {
    private String code;

    private String tokenKey;

    public TokenRequiredException(String tokenKey){
        super();
        this.code = ErrorCode.TOKEN_REQUIRED;
        this.tokenKey = tokenKey;
    }

    public String getCode() {
        return code;
    }

    public String getTokenKey() {
        return tokenKey;
    }

}
