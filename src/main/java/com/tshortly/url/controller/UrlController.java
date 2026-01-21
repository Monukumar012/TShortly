package com.tshortly.url.controller;

import com.tshortly.url.dto.CreateShortUrlRequest;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.service.UrlResolutionService;
import com.tshortly.url.service.UrlShorteningService;
import com.tshortly.common.utlity.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class UrlController {

    private final UrlShorteningService urlShorteningService;
    private final UrlResolutionService urlResolutionService;

    @PostMapping("/shorten")
    public ResponseEntity<ApiResponse<?>> shortUrl(@RequestBody CreateShortUrlRequest createShortUrlRequest) {
        ShortUrlResponse shortUrlResponse =  urlShorteningService.createShortUrl(createShortUrlRequest);
        return ResponseEntity.ok(ApiResponse.success(shortUrlResponse));
    }

    @GetMapping("/{shortUrlCode}")
    public ResponseEntity<?> resolve(@PathVariable("shortUrlCode") String shortUrlCode) {
        String longUrl = urlResolutionService.resolve(shortUrlCode);
        return ResponseEntity.status(302).header("Location", longUrl).build();
    }
}