package com.company_management.common;

import lombok.Data;
@Data
public class ObjectError extends Throwable {
    private String code;
    private String msgError;

    public ObjectError(String code, String msgError) {
        this.code = code;
        this.msgError = msgError;
    }
}
