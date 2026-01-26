package com.tshortly.notification.dto;

import com.tshortly.notification.enums.NotificationStatus;
import com.tshortly.notification.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class NotificationResponse {
    private Long id;
    private String message;
    private NotificationType notificationType;
    private NotificationStatus notificationStatus;
    private LocalDateTime createdAt;
}