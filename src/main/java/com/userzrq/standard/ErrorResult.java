package com.userzrq.standard;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Getter
@Setter
public class ErrorResult {

    private Integer code;
    private String message;
    private Object errors;

    public ErrorResult(ResultCode resultCode, Object errors) {
        setResultCode(resultCode);
        this.errors = errors;
    }

    private void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }


}
