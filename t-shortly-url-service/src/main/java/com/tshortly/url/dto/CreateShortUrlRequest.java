package com.tshortly.url.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShortUrlRequest {
    @NotBlank(message = "{shortUrl.longUrl.notBlank}")
    private String longUrl;
    private boolean trackable;
}