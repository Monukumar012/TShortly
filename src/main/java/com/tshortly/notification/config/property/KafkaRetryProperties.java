package com.tshortly.notification.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.kafka.retry")
public class KafkaRetryProperties {
    private int maxAttempts;
    private long initialIntervalMs;
    private double multiplier;
    private long maxIntervalMs;
}