package com.tshortly.notification.repository;

import com.tshortly.notification.entity.NotificationEntity;
import com.tshortly.notification.enums.NotificationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    boolean existsByEventId(String eventId);
    Page<NotificationEntity> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
    Long countByUserIdAndNotificationStatus(Long userId, NotificationStatus notificationStatus);
}