package com.junuo.pojo;

public class ResponseStatus {
    Integer statusCode;
    String msg;
    String bzText;
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBzText() {
        return bzText;
    }

    public void setBzText(String bzText) {
        this.bzText = bzText;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseStatus{" +
                "statusCode=" + statusCode +
                ", msg='" + msg + '\'' +
                ", bzText='" + bzText + '\'' +
                ", user=" + user +
                '}';
    }
}
