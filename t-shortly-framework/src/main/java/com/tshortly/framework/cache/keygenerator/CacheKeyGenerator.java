package com.tshortly.framework.cache.keygenerator;

import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;


@Component
public class CacheKeyGenerator implements KeyGenerator {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active:dev}")
    private String environment;

    @Override
    @Nonnull
    public Object generate(@Nonnull Object target, Method method, @Nonnull Object... params) {
        String paramPart = Arrays.stream(params)
                .map(String::valueOf)
                .collect(Collectors.joining(":"));

        return applicationName + ":" + environment + ":" + method.getName() + ":" + paramPart;
    }
}