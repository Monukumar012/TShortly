package com.tshortly.events.url;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

public final class ShortUrlAccessedEvent implements Serializable {
    private final UUID eventId;
    private final String shortUrlCode;
    private final Long ownerId;
    private final Instant accessedAt;

    @JsonCreator
    public ShortUrlAccessedEvent(
            @JsonProperty("eventId") UUID eventId,
            @JsonProperty("shortUrlCode") String shortUrlCode,
            @JsonProperty("ownerId") Long ownerId,
            @JsonProperty("accessedAt") Instant accessedAt
    ) {
        this.eventId = eventId;
        this.shortUrlCode = shortUrlCode;
        this.ownerId = ownerId;
        this.accessedAt = accessedAt;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getShortUrlCode() {
        return shortUrlCode;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public Instant getAccessedAt() {
        return accessedAt;
    }

    @Override
    public String toString() {
        return "ShortUrlAccessedEvent{" +
                "eventId=" + eventId +
                ", shortUrlCode='" + shortUrlCode + '\'' +
                ", ownerId=" + ownerId +
                ", accessedAt=" + accessedAt +
                '}';
    }
}