package com.tshortly.common.utlity.resource;

public interface MessageResolver {
    String resolve(String code);
    String resolve(String code, Object...args);
    String resolve(String code, String...args);
}