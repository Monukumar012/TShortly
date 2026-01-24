package com.tshortly.url.controller;

import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.service.UrlShorteningService;
import com.tshortly.common.utlity.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class UrlController {
    private final UrlShorteningService urlShorteningService;
    @PostMapping("/shorten")
    public ResponseEntity<?> shortUrl(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
        ShortUrlResponse shortUrlResponse =  urlShorteningService.createShortUrl(createShortUrlRequest);
        return ResponseEntity.ok(ApiResponse.success(shortUrlResponse));
    }
}