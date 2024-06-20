package com.spa.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShoppingCartException extends RuntimeException{
    private String message;
    public ShoppingCartException(String message) {super(message);}
}
