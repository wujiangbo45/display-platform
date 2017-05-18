package com.mapbar.tds.result;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/18 14:26
 */
public class GenericResponse<T> {

    private String resultCode;

    private String message;

    private T data;

    public GenericResponse() {
        super();
    }

    public GenericResponse(T result) {
        this("200", null, result);
    }

    public GenericResponse(String resultCode, String message, T data) {
        super();
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
