package com.tshortly.framework.message;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PropertyMessageResolver implements MessageResolver {
    private final MessageSource messageSource;

    @Override
    public String resolve(String code) {
        return resolve(code, (Object) null);
    }

    @Override
    public String resolve(String code, String... args) {
        return resolve(code, (Object) args);
    }

    @Override
    public String resolve(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}