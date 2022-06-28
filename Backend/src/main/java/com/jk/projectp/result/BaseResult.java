package com.jk.projectp.result;

import com.jk.projectp.service.ResponseCode;

public class BaseResult<T> {
    private int code;
    private String msg;
    private T data;

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BaseResult(ResponseCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = null;
    }
}
