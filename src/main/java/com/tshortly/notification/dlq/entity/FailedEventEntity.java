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
public class FailedEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalTopic;
    private String eventKey;
    @Column(columnDefinition = "TEXT")
    private String payload;
    @Column(columnDefinition = "TEXT")
    private String errorMessage;
    private boolean replayed;
    private ZonedDateTime createdAt;

    public void markReplayed() {
        this.replayed = true;
    }
}