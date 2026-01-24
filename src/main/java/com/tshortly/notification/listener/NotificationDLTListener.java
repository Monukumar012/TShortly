package com.tshortly.notification.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.dlq.entity.FailedEventEntity;
import com.tshortly.notification.dlq.repository.FailedEventRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@AllArgsConstructor
public class NotificationDLTListener {
    private final FailedEventRepository failedEventRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "short-url-events.DLT",
            groupId = "notification-dlt-service"
    )
    public void onDLTMessage(ShortUrlAccessedEvent event) throws Exception {
        String data = objectMapper.writeValueAsString(event);
        FailedEventEntity failedEventEntity = FailedEventEntity.builder()
                .originalTopic("short-url-events").eventKey(event.getEventId().toString())
                .payload(data).errorMessage("Notification processing failed")
                .createdAt(ZonedDateTime.now()).build();
        failedEventRepository.save(failedEventEntity);
    }
}