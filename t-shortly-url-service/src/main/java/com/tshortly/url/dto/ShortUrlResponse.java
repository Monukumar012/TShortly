package com.tshortly.url.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ShortUrlResponse {
    private String shortUrlCode;
    private String longUrl;
    private LocalDateTime createdAt;
}