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

    public BaseResult(ResponseCode code){
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = null;
    }
}
