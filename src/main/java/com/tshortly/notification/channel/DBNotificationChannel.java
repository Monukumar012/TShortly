package com.tshortly.notification.channel;

import com.tshortly.notification.entity.NotificationEntity;
import com.tshortly.notification.enums.NotificationStatus;
import com.tshortly.notification.enums.NotificationType;
import com.tshortly.notification.model.NotificationMessage;
import com.tshortly.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DBNotificationChannel implements NotificationChannel{
    private final NotificationRepository notificationRepository;
    @Override
    public void send(NotificationMessage message) {
        if(notificationRepository.existsByEventId(message.getEventId())) {
            return;
        }
        NotificationEntity notification = NotificationEntity.builder()
                .userId(message.getUserId()).message(message.getMessage())
                .eventId(message.getEventId()).createdAt(message.getCreatedAt())
                .notificationStatus(NotificationStatus.UNREAD)
                .notificationType(NotificationType.URL_ACCESSED)
                .build();
        notificationRepository.save(notification);
    }
}