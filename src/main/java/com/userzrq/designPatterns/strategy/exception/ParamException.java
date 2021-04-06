package com.userzrq.designPatterns.strategy.exception;

public class ParamException extends RuntimeException {

    private String msg;

    public ParamException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ParamException(String msg, Throwable e) {
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
