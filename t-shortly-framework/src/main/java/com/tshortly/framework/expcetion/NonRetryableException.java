package com.tshortly.framework.expcetion;

public class NonRetryableException extends RuntimeException{
    public NonRetryableException() {
        super();
    }

    public NonRetryableException(String msg) {
        super(msg);
    }
}