package com.tshortly.notification.dlq.controller;

import com.tshortly.common.utlity.ApiResponse;
import com.tshortly.notification.dlq.service.FailedEventService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
@RequestMapping("/api/dlq")
@AllArgsConstructor
public class FailedEventController {
    private final FailedEventService failedEventService;

    @PostMapping("/{failedEventId}/reply")
    public ResponseEntity<?> replay(@PathVariable Long failedEventId) throws Exception {
        failedEventService.replay(failedEventId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}