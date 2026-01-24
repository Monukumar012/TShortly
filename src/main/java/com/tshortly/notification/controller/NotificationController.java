package com.tshortly.notification.controller;

import com.tshortly.common.utlity.ApiResponse;
import com.tshortly.notification.dto.UnreadCountResponse;
import com.tshortly.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    @GetMapping
    public ResponseEntity<?> getNotifications(@RequestParam("userId") Long userId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(notificationService.getNotifications(userId, PageRequest.of(page, size))));
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<?> unReadCount(@RequestParam Long userId) {
        UnreadCountResponse unreadCountResponse = UnreadCountResponse.builder()
                .count(notificationService.getUnReadNotificationCount(userId)).build();
        return ResponseEntity.ok(ApiResponse.success(unreadCountResponse));
    }

}