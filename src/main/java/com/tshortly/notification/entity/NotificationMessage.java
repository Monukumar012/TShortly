package com.tshortly.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class NotificationMessage {
    private final Long userId;
    private final String message;
    private final Instant createdAt;
}