package com.tshortly.notification.channel;

import com.tshortly.notification.entity.NotificationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogNotificationChannel implements NotificationChannel{
    private static final Logger log =
            LoggerFactory.getLogger(LogNotificationChannel.class);
    @Override
    public void send(NotificationMessage message) {
        log.info(
                "[NOTIFICATION] userId={}, message='{}', at={}",
                message.getUserId(),
                message.getMessage(),
                message.getCreatedAt()
        );
    }
}