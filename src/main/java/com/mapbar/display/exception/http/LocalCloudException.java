package com.mapbar.display.exception.http;

import com.mapbar.display.exception.ErrorCode;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 10:36
 */
public class LocalCloudException extends HttpRequestException {

    private String reasonPhrase;

    public LocalCloudException(String errorCode, String reasonPhrase) {
        super(errorCode);
        this.reasonPhrase = reasonPhrase;
    }


    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String getMessage() {
        return "ReasonPhrase: " + this.reasonPhrase;
    }

}
