package com.tshortly.notification.orchestrator;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.channel.NotificationChannel;
import com.tshortly.notification.model.NotificationMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationOrchestrator {
    private final List<NotificationChannel> channels;
    public void handle(ShortUrlAccessedEvent event) {
        NotificationMessage message =
                new NotificationMessage(
                        event.getOwnerId(),
                        "Your short URL '" + event.getShortUrlCode() + "' was accessed",
                        ZonedDateTime.now(),
                        event.getEventId().toString()
                );

        for(NotificationChannel channel: channels) {
            channel.send(message);
        }
    }
}