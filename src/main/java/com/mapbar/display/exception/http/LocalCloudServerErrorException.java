package com.mapbar.display.exception.http;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 11:09
 */
public class LocalCloudServerErrorException extends LocalCloudException{

    private String message = "Server端内部错误";

    public LocalCloudServerErrorException(){
        super("500","Server端内部错误");
    }

    public LocalCloudServerErrorException(String message){
        super("500",message);
    }
    @Override
    public String getMessage() {
        return "ReasonPhrase: " + this.message;
    }
}
