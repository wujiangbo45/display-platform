package com.mapbar.common.exception.business;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/24 9:47
 */
public class UserCheckException extends RuntimeException {

    private String code;

    public UserCheckException(String code, String message){
        super(message);
        this.code = code;
    }
    public UserCheckException(String code){
        this.code = code;
    }

    @Override
    public String getMessage() {
        return "登录失败：" + super.getMessage();
    }

    public String getCode() {
        return code;
    }

}
