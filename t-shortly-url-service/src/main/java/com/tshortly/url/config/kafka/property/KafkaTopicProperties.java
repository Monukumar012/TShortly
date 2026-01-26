package com.tshortly.url.config.kafka.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
@ConfigurationProperties(prefix = "app.kafka.topics")
public class KafkaTopicProperties {
    private String shortUrlEvents;
}