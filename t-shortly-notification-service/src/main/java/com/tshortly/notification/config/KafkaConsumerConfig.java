package com.tshortly.notification.config;

import com.tshortly.framework.expcetion.NonRetryableException;
import com.tshortly.notification.config.property.KafkaDLTProperties;
import com.tshortly.notification.config.property.KafkaRetryProperties;
import org.apache.kafka.common.TopicPartition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.ExponentialBackOff;

import java.net.SocketTimeoutException;

@Configuration
public class KafkaConsumerConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConsumerFactory<Object, Object> consumerFactory,
            KafkaTemplate<String, Object> kafkaTemplate,
            KafkaRetryProperties kafkaRetryProperties,
            KafkaDLTProperties kafkaDLTProperties
    ) {
        // 1. Where to send failed messages
        DefaultErrorHandler errorHandler = getDefaultErrorHandler(kafkaTemplate, kafkaRetryProperties, kafkaDLTProperties);
        errorHandler.addNotRetryableExceptions(NonRetryableException.class, IllegalArgumentException.class, NullPointerException.class);
        errorHandler.addRetryableExceptions(SocketTimeoutException.class);

        // 3. Attach to listener
        ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setConsumerFactory(consumerFactory);
        containerFactory.setCommonErrorHandler(errorHandler);
        return containerFactory;
    }

    private DefaultErrorHandler getDefaultErrorHandler(KafkaTemplate<String, Object> kafkaTemplate, KafkaRetryProperties kafkaRetryProperties, KafkaDLTProperties kafkaDLTProperties) {
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(kafkaTemplate,
                        ((record, exception) ->
                                new TopicPartition(record.topic() + kafkaDLTProperties.getSuffix(), record.partition())
                        )
                );

        // 2. Retry policy
        ExponentialBackOff backOff = new ExponentialBackOff(kafkaRetryProperties.getInitialIntervalMs(), kafkaRetryProperties.getMaxAttempts());
        backOff.setMaxInterval(kafkaRetryProperties.getMaxIntervalMs());
        backOff.setMaxElapsedTime(kafkaRetryProperties.getInitialIntervalMs() * kafkaRetryProperties.getMaxAttempts());
        return new DefaultErrorHandler(recoverer, backOff);
    }
}