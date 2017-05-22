package com.mapbar.display.dto;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 8:53
 */
public class LocalCloudRespopnse<T> extends BaseResp{

    private String message;

    private T data;


    LocalCloudRespopnse(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    LocalCloudRespopnse (T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
