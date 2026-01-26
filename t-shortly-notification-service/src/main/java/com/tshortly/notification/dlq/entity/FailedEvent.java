package com.tshortly.notification.dlq.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "failed_events")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FailedEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTopic;
    private String eventKey;
    @Column(columnDefinition = "TEXT")
    private String payloadJson;
    @Column(columnDefinition = "TEXT")
    private String exceptionMessage;
    private String exceptionClass;
    private boolean replayed;
    private ZonedDateTime createdAt;

    public void markReplayed() {
        this.replayed = true;
    }
}