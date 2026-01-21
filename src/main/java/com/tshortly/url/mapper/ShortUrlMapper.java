package com.tshortly.url.mapper;

import com.tshortly.url.dto.ShortUrlDTO;
import com.tshortly.url.dto.ShortUrlResponse;
import com.tshortly.url.entity.ShortUrl;
import com.tshortly.common.utlity.DateUtility;

public class ShortUrlMapper {
    public static ShortUrlDTO toDTO(ShortUrl entity) {
        return ShortUrlDTO.builder()
                .id(entity.getId())
                .shortUrlCode(entity.getShortUrlCode())
                .longUrl(entity.getLongUrl())
                .trackable(entity.isTrackable())
                .createdAt(DateUtility.toLocalDateTime(entity.getCreatedAt()))
                .build();
    }

    public static ShortUrlResponse toResponse(ShortUrl savedShortUrl) {
        return ShortUrlResponse.builder()
                .shortUrlCode(savedShortUrl.getShortUrlCode())
                .longUrl(savedShortUrl.getLongUrl())
                .createdAt(DateUtility.toLocalDateTime(savedShortUrl.getCreatedAt()))
                .build();
    }
}