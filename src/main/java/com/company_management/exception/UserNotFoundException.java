package com.company_management.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException
{
    private String code;
    private String message;
    public UserNotFoundException(String message)
    {
        super(message);
    }
}