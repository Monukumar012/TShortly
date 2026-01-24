package com.tshortly.notification.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class NotificationMessage {
    private final Long userId;
    private final String message;
    private final ZonedDateTime createdAt;
    private final String eventId;
}