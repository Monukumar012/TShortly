package com.tshortly.url.service;

import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.entity.ShortUrl;
import com.tshortly.url.exception.CodeGenerationException;
import com.tshortly.url.mapper.ShortUrlMapper;
import com.tshortly.url.repository.UrlRepository;
import com.tshortly.url.startegy.ShortCodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UrlShorteningService {
    private final UrlRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;
    private final Integer MAX_ATTEMPTS = 5;


    public ShortUrlResponse createShortUrl(CreateShortUrlRequest createShortUrlRequest) {
        String shortUrlCode = generateUniqueShortUrlCode();
        ShortUrl shortUrl = ShortUrl.create(shortUrlCode, createShortUrlRequest.getLongUrl(), createShortUrlRequest.getOwnerId(), createShortUrlRequest.isTrackable());
        ShortUrl savedShortUrl = urlRepository.save(shortUrl);
        return ShortUrlMapper.toResponse(savedShortUrl);
    }

    private String generateUniqueShortUrlCode() {
        int attempts = 0;
        while(attempts++ < MAX_ATTEMPTS) {
            String shortUrlCode = shortCodeGenerator.generate();
            if(!urlRepository.existsByShortUrlCode(shortUrlCode)) {
                return shortUrlCode;
            }
        }
        throw new CodeGenerationException("Unable to generate unique short URL");
    }
}