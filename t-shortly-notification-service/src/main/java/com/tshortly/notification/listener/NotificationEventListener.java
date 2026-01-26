package com.tshortly.notification.listener;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.framework.expcetion.NonRetryableException;
import com.tshortly.notification.orchestrator.NotificationOrchestrator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class NotificationEventListener {
    private final NotificationOrchestrator orchestrator;
    private final Validator validator;

    @KafkaListener(
            topics = "#{@kafkaTopicProperties.shortUrlEvents}",
            groupId = "#{@kafkaConsumerGroupProperties.notification}"
    )
    public void onMessage(ShortUrlAccessedEvent event) {
        Set<ConstraintViolation<ShortUrlAccessedEvent>> violations = validator.validate(event);
        if(!violations.isEmpty()) {
            throw new NonRetryableException(violations.iterator().next().getMessage());
        }
        orchestrator.handle(event);
    }
}