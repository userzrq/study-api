package com.userzrq.reflection.annotaionDemo.exception;

public class PrivilegeException extends RuntimeException {

    private String msg;

    public PrivilegeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PrivilegeException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
