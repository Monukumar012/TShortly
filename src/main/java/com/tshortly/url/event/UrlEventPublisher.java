package com.tshortly.url.event;

import com.tshortly.events.url.ShortUrlAccessedEvent;

public interface UrlEventPublisher {
    void publish(ShortUrlAccessedEvent event);
}