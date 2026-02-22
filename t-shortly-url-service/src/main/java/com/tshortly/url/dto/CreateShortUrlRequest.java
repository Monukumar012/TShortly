package com.tshortly.url.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateShortUrlRequest {
    @NotBlank(message = "{shortUrl.longUrl.notBlank}")
    private String longUrl;
    private boolean trackable;
    @Length(min = 5, message = "{shortUrl.alias.minLength}")
    private String alias;
}