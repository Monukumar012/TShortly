package com.tshortly.notification.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.notification.config.property.KafkaDLTProperties;
import com.tshortly.notification.dlq.entity.FailedEvent;
import com.tshortly.notification.dlq.repository.FailedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
public class NotificationDLTListener {
    private final FailedEventRepository failedEventRepository;
    private final ObjectMapper objectMapper;
    private final KafkaDLTProperties kafkaDLTProperties;

    @KafkaListener(
            topics = "#{@kafkaTopicProperties.shortUrlEvents + @kafkaDLTProperties.suffix}",
            groupId = "#{kafkaConsumerGroupProperties.notificationDlt}"
    )
    public void onDLTMessage(ShortUrlAccessedEvent event,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             @Header(KafkaHeaders.RECEIVED_KEY) String key,
                             @Header(KafkaHeaders.EXCEPTION_CAUSE_FQCN) Exception exception) throws Exception {
        String data = objectMapper.writeValueAsString(event);
        FailedEvent failedEvent = FailedEvent.builder()
                .originalTopic(topic.replace(kafkaDLTProperties.getSuffix(), ""))
                .eventKey(key).payloadJson(data)
                .exceptionClass(exception.getClass().getName())
                .exceptionMessage(exception.getMessage())
                .createdAt(ZonedDateTime.now()).build();
        failedEventRepository.save(failedEvent);
    }
}