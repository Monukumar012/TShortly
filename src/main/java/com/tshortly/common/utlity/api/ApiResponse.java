package com.tshortly.common.utlity.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApiResponse <T> {
    private boolean success;
    private T data;
    private List<ApiMessage> messages;

    public static <T> ApiResponse<T> success(ApiMessage message) {
        return new ApiResponse<>(true, null, List.of(message));
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, Collections.emptyList());
    }

    public static <T> ApiResponse<T> success(T data, ApiMessage message) {
        return new ApiResponse<>(true, data, List.of(message));
    }

    public static <T> ApiResponse<T> failure(ApiMessage error) {
        return new ApiResponse<>(false, null, List.of(error));
    }

    public static <T> ApiResponse<T> failure(List<ApiMessage> errors) {
        return new ApiResponse<>(false, null, errors);
    }
}