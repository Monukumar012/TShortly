package com.tshortly.exception;

public class NonRetryableException extends RuntimeException{
    public NonRetryableException() {
        super();
    }

    public NonRetryableException(String msg) {
        super(msg);
    }
}