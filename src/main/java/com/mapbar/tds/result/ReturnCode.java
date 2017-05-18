package com.mapbar.tds.result;

/**
 * 通用Http code 封装
 *
 * @author chenjc
 * @date 2016-03-02
 * @modify
 * @copyright Navi Tsp
 */
public enum ReturnCode implements InterfaceResultCode {

    OK(200, "success"),
    CLIENT_ERROR(400, "failure"),
    SERVER_ERROR(500, "Server Error"),
    /**
     * used in cases when data was concurrently modified, for example when version value of passed data
     * differ from version value of stored data.
     */
    CONFLICT(409, "Conflict");


    private final int code;

    private String message;

    ReturnCode(int code) {
        this.code = code;
    }

    ReturnCode(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return message;
    }


}
