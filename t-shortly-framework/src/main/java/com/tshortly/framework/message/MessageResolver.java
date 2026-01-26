package com.tshortly.framework.message;

public interface MessageResolver {
    String resolve(String code);
    String resolve(String code, Object...args);
    String resolve(String code, String...args);
}