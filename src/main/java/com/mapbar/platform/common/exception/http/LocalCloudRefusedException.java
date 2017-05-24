package com.mapbar.platform.common.exception.http;

/**
 * @Author: wujiangbo
 * @Create: 2017/05/22 11:05
 */
public class LocalCloudRefusedException extends LocalCloudException{

    public LocalCloudRefusedException() {
        super("403", "位置云服务拒绝访问");
    }

    public LocalCloudRefusedException(String message) {
        super("403", message);
    }

}
