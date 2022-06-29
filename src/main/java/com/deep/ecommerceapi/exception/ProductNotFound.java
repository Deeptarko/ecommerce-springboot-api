package com.deep.ecommerceapi.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFound extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ProductNotFound(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ProductNotFound(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
