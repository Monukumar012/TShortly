package com.tshortly.framework.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiMessage {
    private String code;
    private String message;

    public static ApiMessage of(String code, String msg) {
        return new ApiMessage(code, msg);
    }
}