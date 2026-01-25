package com.tshortly.notification.dlq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.exception.EntityNotFoundException;
import com.tshortly.notification.dlq.entity.FailedEvent;
import com.tshortly.notification.dlq.repository.FailedEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FailedEventService {
    private final ObjectMapper objectMapper;
    private final FailedEventRepository failedEventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void replay(Long failedEventId) throws Exception{
        FailedEvent failedEvent = failedEventRepository.findById(failedEventId)
                .orElseThrow(() -> new EntityNotFoundException("Failed event not found"));
        // already replayed
        if(failedEvent.isReplayed()) {
            return;
        }
        ShortUrlAccessedEvent event = objectMapper.readValue(failedEvent.getPayloadJson(), ShortUrlAccessedEvent.class);
        kafkaTemplate.send(failedEvent.getOriginalTopic(), failedEvent.getEventKey(), event);
        failedEvent.markReplayed();
        failedEventRepository.save(failedEvent);
    }
}