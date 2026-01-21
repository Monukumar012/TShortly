package com.tshortly.url.event;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Primary
public class KafkaUrlEventPublisher implements UrlEventPublisher{
    private static final String TOPIC = "short-url-events";
    private final KafkaTemplate<String, ShortUrlAccessedEvent> kafkaTemplate;
    @Override
    public void publish(ShortUrlAccessedEvent event) {
        kafkaTemplate.send(TOPIC, event.getShortUrlCode(), event);
    }
}