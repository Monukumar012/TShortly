package com.tshortly.url.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ShortUrlDTO {
    private Long id;
    private String shortUrlCode;
    private String longUrl;
    private Long ownerId;
    private Boolean trackable;
    private LocalDateTime createdAt;

}