package com.mapbar.display.exception.http;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 10:42
 */
public class LocalCloudParamErrorException extends LocalCloudException {


    private String message = "请求参数不正确，如json格式不正确，或必填得参数没有携带，或者值域无效";

    public LocalCloudParamErrorException(){
        super("400","请求参数不正确，如json格式不正确，或必填得参数没有携带，或者值域无效");
    }

    @Override
    public String getMessage() {
        return "ReasonPhrase: " + this.message;
    }
}
