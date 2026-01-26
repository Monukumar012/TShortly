package com.tshortly.framework.api;

import com.tshortly.framework.message.MessageResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApiMessageFactory {
    private static final String DEFAULT_SUCCESS_CODE = "default.success.msg";
    private static final String DEFAULT_ERROR_CODE = "default.error.msg";
    private final MessageResolver messageResolver;

    public ApiMessage defaultSuccess() {
        return ApiMessage.of(DEFAULT_SUCCESS_CODE, messageResolver.resolve(DEFAULT_SUCCESS_CODE));
    }
    public ApiMessage defaultError() {
        return ApiMessage.of(DEFAULT_ERROR_CODE, messageResolver.resolve(DEFAULT_ERROR_CODE));
    }
    public ApiMessage of(String code) {
        return ApiMessage.of(code, messageResolver.resolve(code));
    }

    public ApiMessage of(String code, String...args) {
        return ApiMessage.of(code, messageResolver.resolve(code, args));
    }

    public ApiMessage of(String code, Object...args) {
        return ApiMessage.of(code, messageResolver.resolve(code, args));
    }
}