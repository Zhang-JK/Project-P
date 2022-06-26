package com.jk.projectp.service;

public enum ResponseCode {

    SUCCESS(200, "Success"),
    LIMIT_EXCEED(400, "Limit exceed max"),
    LOGIN_USER_NOT_EXIST(1001, "User not exist"),
    LOGIN_WRONG_PASSWORD(1002, "Wrong password");

    private final int code;
    private final String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
