package com.tshortly.url.controller;

import com.tshortly.url.service.UrlResolutionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RedirectController {

    private final UrlResolutionService urlResolutionService;
    @GetMapping("/{shortUrlCode}")
    public ResponseEntity<?> resolve(@PathVariable("shortUrlCode") String shortUrlCode) {
        String longUrl = urlResolutionService.resolve(shortUrlCode);
        return ResponseEntity.status(302).header("Location", longUrl).build();
    }
}