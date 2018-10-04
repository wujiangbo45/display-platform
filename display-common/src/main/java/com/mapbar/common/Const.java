package com.mapbar.common;

/**
 * 常量类
 * @Author: wujiangbo
 * @Create: 2017/05/19 13:11
 */
public class Const {

    public static final Integer MAX_CONN_PER_HOST = 3;
    public static final Integer MAX_TOTAL_CONNECTIONS = 10;
    public static final int CONNECTION_TIMEOUT = 20000;
    public static final int SO_TIME_OUT = 20000;


    public static final String RESP_REALTIME_POSITION_TYPE = "FeatureCollection";

    public static final String RESP_FEATURE_TYPE = "Feature";

    public static final Long TOKEN_LIVE_TIME_MINUTE = 30L;
    public static final String TOKEN_KEY_PREFX = "stoken_";
    public static final String USER_KEY_PREFX = "user_";
    public static final String SUCCESS_MESSAGE = "请求成功";

    public static final String LOCATION_DATA_KEY = "LocationData";
    public static final String WORK_ORDER_DATA_KEY = "workOrderData";
    public static final String WORK_ORDER_DATA_KEY_BY_DAY = "workOrderDataByDay";

}
