package com.tshortly.url.service;

import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.entity.ShortUrl;
import com.tshortly.url.exception.CodeGenerationException;
import com.tshortly.url.mapper.ShortUrlMapper;
import com.tshortly.url.repository.UrlRepository;
import com.tshortly.url.startegy.ShortCodeGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UrlShorteningService {
    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;
    @Value(value = "${app.url.shortening.max-attempts}")
    private int maxAttempts;


    public ShortUrlResponse createShortUrl(CreateShortUrlRequest createShortUrlRequest, Long ownerId) {
        boolean finalTrackable = ownerId != null && createShortUrlRequest.isTrackable();
        // if short url created by same user with tracking return existing one
        if(ownerId != null && finalTrackable) {
            Optional<ShortUrl> existingShortUrl = urlRepository.findByOwnerIdAndLongUrlAndTrackableTrue(ownerId, createShortUrlRequest.getLongUrl());
            if(existingShortUrl.isPresent()) {
                return ShortUrlMapper.toResponse(existingShortUrl.get());
            }
        }
        String shortUrlCode = generateUniqueShortUrlCode();
        ShortUrl shortUrl = ShortUrl.create(shortUrlCode, createShortUrlRequest.getLongUrl(), ownerId, finalTrackable);
        ShortUrl savedShortUrl = urlRepository.save(shortUrl);
        return ShortUrlMapper.toResponse(savedShortUrl);
    }

    private String generateUniqueShortUrlCode() {
        int attempts = 0;
        while(attempts++ < maxAttempts) {
            String shortUrlCode = shortCodeGenerator.generate();
            if(!urlRepository.existsByShortUrlCode(shortUrlCode)) {
                return shortUrlCode;
            }
        }
        throw new CodeGenerationException("Unable to generate unique short URL");
    }
}