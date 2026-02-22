package com.tshortly.url.exception;

public class AliasNotAvailableException extends RuntimeException{
    public AliasNotAvailableException() {
        super();
    }

    public AliasNotAvailableException(String message) {
        super(message);
    }

}