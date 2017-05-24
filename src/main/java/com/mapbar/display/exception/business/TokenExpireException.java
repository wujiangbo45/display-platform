package com.mapbar.display.exception.business;

import com.mapbar.display.exception.ErrorCode;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 11:31
 */
public class TokenExpireException extends RuntimeException {
    private String code;

    private String tokenKey;

    public TokenExpireException(String tokenKey){
        super();
        this.code = ErrorCode.TOKEN_EXPIRE;
        this.tokenKey = tokenKey;
    }

    public String getCode() {
        return code;
    }

    public String getTokenKey() {
        return tokenKey;
    }

}
