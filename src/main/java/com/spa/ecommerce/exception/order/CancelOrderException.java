package com.spa.ecommerce.exception.order;

public class CancelOrderException extends RuntimeException {
    private String errorCode;

    public CancelOrderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

