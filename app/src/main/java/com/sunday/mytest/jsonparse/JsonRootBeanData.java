package com.sunday.mytest.jsonparse;

public class JsonRootBeanData {
    private String qrcode;
    private String expire;
    private String sid;
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public String getQrcode() {
        return qrcode;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }
    public String getExpire() {
        return expire;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getSid() {
        return sid;
    }
}
