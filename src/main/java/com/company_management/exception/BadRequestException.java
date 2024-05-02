package com.company_management.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private String message;
    public BadRequestException(String message) {
        super(message);
    }
}
