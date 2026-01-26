package com.tshortly.notification.entity;

import com.tshortly.notification.enums.NotificationStatus;
import com.tshortly.notification.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(
        name = "notifications",
        indexes = {
                @Index(name = "idx_user_id", columnList = "userId"),
                @Index(name = "idx_event_id", columnList = "eventId", unique = true)
        }
)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@TableGenerator(name="NOTIFICATION_ID_GENERATOR", pkColumnValue = "EVENT_ID", initialValue=100000, allocationSize=1)
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="NOTIFICATION_ID_GENERATOR")
    private Long id;
    private Long userId;
    @Column(nullable = false, length = 500)
    private String message;
    private ZonedDateTime createdAt;
    @Column(nullable = false, unique = true)
    private String eventId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NotificationStatus notificationStatus;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private NotificationType notificationType;

    public void markAsRead() {
        this.notificationStatus = NotificationStatus.READ;
    }
}