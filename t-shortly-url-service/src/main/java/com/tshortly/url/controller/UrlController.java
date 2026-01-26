package com.tshortly.url.controller;

import com.tshortly.framework.api.ApiMessageFactory;
import com.tshortly.framework.api.ApiResponse;
import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.service.UrlShorteningService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlShorteningService urlShorteningService;
    private final ApiMessageFactory apiMessageFactory;

    @PostMapping("/shorten")
    public ResponseEntity<?> shortUrl(@Valid @RequestBody CreateShortUrlRequest createShortUrlRequest) {
        ShortUrlResponse shortUrlResponse =  urlShorteningService.createShortUrl(createShortUrlRequest, 1000L);
        return ResponseEntity.ok(ApiResponse.success(shortUrlResponse, apiMessageFactory.defaultSuccess()));
    }
}