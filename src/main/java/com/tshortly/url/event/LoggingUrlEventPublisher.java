package com.tshortly.url.event;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingUrlEventPublisher implements UrlEventPublisher{
    private static final Logger log = LoggerFactory.getLogger(LoggingUrlEventPublisher.class);
    @Override
    public void publish(ShortUrlAccessedEvent event) {
        log.info("EVENT PUBLISHED: {}", event);
    }
}