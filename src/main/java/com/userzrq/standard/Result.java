package com.userzrq.standard;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Result implements Serializable {

    private Integer code;
    private String message;
    private Object data;

    Result(ResultCode resultCode, Object data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public static Result failure(Integer code, String message, Object errors) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        result.setData(errors);
        return result;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    /**
     * 返回成功（无data）
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回成功（有data）
     *
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }


    /**
     * 返回失败（无data）
     * 错误返回状态比较多，手动传入
     */
    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败（有data）
     */
    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }
}
