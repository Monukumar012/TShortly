package com.tshortly.url.exception;

public class UrlNotFoundException extends RuntimeException{
    public UrlNotFoundException() {
        super();
    }

    public UrlNotFoundException(String message) {
        super(message);
    }

}