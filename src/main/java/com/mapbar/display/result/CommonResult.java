package com.mapbar.display.result;

/**
 * Created by yinsihua on 2017/5/2.
 */
public class CommonResult {

    private int Code;
    private String Errmsg;

    public CommonResult() {
    }

    public CommonResult(Integer resultCode, String message) {
        this.Code = resultCode.intValue();
        this.Errmsg = message;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getErrmsg() {
        return Errmsg;
    }

    public void setErrmsg(String errmsg) {
        Errmsg = errmsg;
    }
    public <T extends CommonResult> T fillResult(InterfaceResultCode result) {
        this.setCode(result.code());
        this.setErrmsg(result.message());
        return (T) this;
    }
    @Override
    public String toString() {
        return "CommonResult{" +
                "Code=" + Code +
                ", Errmsg='" + Errmsg + '\'' +
                '}';
    }
}
