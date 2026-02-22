package com.tshortly.url.service;

import com.tshortly.events.url.ShortUrlAccessedEvent;
import com.tshortly.url.entity.ShortUrl;
import com.tshortly.url.event.UrlEventPublisher;
import com.tshortly.url.exception.UrlNotFoundException;
import com.tshortly.url.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UrlResolutionService {
    private final UrlRepository urlRepository;
    private final UrlEventPublisher urlEventPublisher;

    @Cacheable(value = "shortUrl", keyGenerator = "cacheKeyGenerator")
    public String resolve(String shortUrlCode) {
        ShortUrl shortUrl = urlRepository.findByShortUrlCode(shortUrlCode).orElseThrow(UrlNotFoundException::new);
        // Publish the event
        if(shortUrl.isTrackable()) {
            ShortUrlAccessedEvent shortUrlAccessedEvent = new ShortUrlAccessedEvent(UUID.randomUUID(), shortUrl.getShortUrlCode(), shortUrl.getOwnerId(), ZonedDateTime.now());
            urlEventPublisher.publish(shortUrlAccessedEvent);
        }
        return shortUrl.getLongUrl();
    }

}