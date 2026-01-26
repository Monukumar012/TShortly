package com.tshortly.url.event;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.config.property.KafkaTopicProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Primary
public class KafkaUrlEventPublisher implements UrlEventPublisher{
    private final KafkaTopicProperties kafkaTopicProperties;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public void publish(ShortUrlAccessedEvent event) {
        kafkaTemplate.send(kafkaTopicProperties.getShortUrlEvents(), event.getShortUrlCode(), event);
    }
}