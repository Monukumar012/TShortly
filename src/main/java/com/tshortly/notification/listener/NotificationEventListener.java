package com.tshortly.notification.listener;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.service.NotificationOrchestrator;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NotificationEventListener {
    private final NotificationOrchestrator orchestrator;

    @KafkaListener(
            topics = "short-url-events",
            groupId = "notification-service"
    )
    public void onMessage(ShortUrlAccessedEvent event) {
        orchestrator.handle(event);
    }
}