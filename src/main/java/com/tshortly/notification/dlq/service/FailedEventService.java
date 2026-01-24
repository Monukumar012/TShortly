package com.tshortly.notification.dlq.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.exception.EntityNotFoundException;
import com.tshortly.notification.dlq.entity.FailedEventEntity;
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
        FailedEventEntity failedEventEntity = failedEventRepository.findById(failedEventId)
                .orElseThrow(() -> new EntityNotFoundException("Failed event not found"));
        // already replayed
        if(failedEventEntity.isReplayed()) {
            return;
        }
        ShortUrlAccessedEvent event = objectMapper.readValue(failedEventEntity.getPayload(), ShortUrlAccessedEvent.class);
        kafkaTemplate.send(failedEventEntity.getOriginalTopic(), failedEventEntity.getEventKey(), event);
        failedEventEntity.markReplayed();
        failedEventRepository.save(failedEventEntity);
    }
}