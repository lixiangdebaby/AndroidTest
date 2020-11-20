package com.sunday.mytest.jsonparse;

public class JsonRootBeanQrCode {
    private String code;
    private String msg;
    private JsonRootBeanData data;
    private String hcmData;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(JsonRootBeanData data) {
        this.data = data;
    }
    public JsonRootBeanData getData() {
        return data;
    }

    public void setHcmData(String hcmData) {
        this.hcmData = hcmData;
    }
    public String getHcmData() {
        return hcmData;
    }

}
