package com.tshortly.url.controller;

import com.tshortly.common.utlity.api.ApiMessageFactory;
import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.service.UrlShorteningService;
import com.tshortly.common.utlity.api.ApiResponse;
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
        ShortUrlResponse shortUrlResponse =  urlShorteningService.createShortUrl(createShortUrlRequest, null);
        return ResponseEntity.ok(ApiResponse.success(shortUrlResponse, apiMessageFactory.defaultSuccess()));
    }
}