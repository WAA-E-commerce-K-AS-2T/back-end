package com.spa.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UnAuthorizedException extends RuntimeException{
    private String message;
    public UnAuthorizedException(String message) {
        super(message);
    }
}
