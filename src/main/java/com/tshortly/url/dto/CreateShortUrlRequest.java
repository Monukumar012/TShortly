package com.tshortly.url.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShortUrlRequest {
    private String longUrl;
    private boolean trackable;
    private Long ownerId;
}