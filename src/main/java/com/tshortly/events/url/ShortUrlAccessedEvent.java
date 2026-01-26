package com.tshortly.events.url;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
public final class ShortUrlAccessedEvent implements Serializable {
    @NotNull(message = "{notification.eventId.notNull}")
    private final UUID eventId;
    @NotBlank(message = "{notification.shortCode.notBlank}")
    private final String shortUrlCode;
    private final Long ownerId;
    private final ZonedDateTime accessedAt;

    @JsonCreator
    public ShortUrlAccessedEvent(
            @JsonProperty("eventId") UUID eventId,
            @JsonProperty("shortUrlCode") String shortUrlCode,
            @JsonProperty("ownerId") Long ownerId,
            @JsonProperty("accessedAt") ZonedDateTime accessedAt
    ) {
        this.eventId = eventId;
        this.shortUrlCode = shortUrlCode;
        this.ownerId = ownerId;
        this.accessedAt = accessedAt;
    }
}