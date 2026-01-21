package com.tshortly.notification.service;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.channel.NotificationChannel;
import com.tshortly.notification.entity.NotificationMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class NotificationOrchestrator {
    private final List<NotificationChannel> channels;
    public void handle(ShortUrlAccessedEvent event) {
        NotificationMessage message =
                new NotificationMessage(
                        event.getOwnerId(),
                        "Your short URL '" + event.getShortUrlCode() + "' was accessed",
                        Instant.now()
                );

        for(NotificationChannel channel: channels) {
            channel.send(message);
        }
    }
}