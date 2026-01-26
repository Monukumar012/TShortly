package com.tshortly.notification.service;

import com.tshortly.framework.expcetion.EntityNotFoundException;
import com.tshortly.framework.util.DateUtility;
import com.tshortly.notification.dto.NotificationResponse;
import com.tshortly.notification.entity.NotificationEntity;
import com.tshortly.notification.enums.NotificationStatus;
import com.tshortly.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Page<NotificationResponse> getNotifications(Long userId, Pageable pageable) {
        return notificationRepository.findByUserIdOrderByCreatedAtDesc(userId, pageable).map(this::toResponse);
    }

    public void markAsRead(Long notificationId) {
        NotificationEntity notification = notificationRepository.findById(notificationId)
                .orElseThrow(EntityNotFoundException::new);
        if(notification.getNotificationStatus() == NotificationStatus.READ) {
            return;
        }
        notification.markAsRead();
        notificationRepository.save(notification);
    }

    private NotificationResponse toResponse(NotificationEntity notification) {
        return NotificationResponse.builder().id(notification.getId())
                .message(notification.getMessage()).notificationType(notification.getNotificationType())
                .notificationStatus(notification.getNotificationStatus())
                .createdAt(DateUtility.toLocalDateTime(notification.getCreatedAt()))
                .build();
    }

    public Long getUnReadNotificationCount(Long userId) {
        return notificationRepository.countByUserIdAndNotificationStatus(userId, NotificationStatus.UNREAD);
    }
}