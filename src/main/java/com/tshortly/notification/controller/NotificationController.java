package com.tshortly.notification.controller;

import com.tshortly.common.utlity.api.ApiMessageFactory;
import com.tshortly.common.utlity.api.ApiResponse;
import com.tshortly.notification.dto.NotificationResponse;
import com.tshortly.notification.dto.UnreadCountResponse;
import com.tshortly.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;
    private final ApiMessageFactory apiMessageFactory;
    @GetMapping
    public ResponseEntity<?> getNotifications(@RequestParam("userId") Long userId,
                                              @RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
        Page<NotificationResponse> notificationResponseList = notificationService.getNotifications(userId, PageRequest.of(page, size));
        return ResponseEntity.ok(ApiResponse.success(notificationResponseList, apiMessageFactory.defaultSuccess()));
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(ApiResponse.success(apiMessageFactory.defaultSuccess()));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<?> unReadCount(@RequestParam Long userId) {
        UnreadCountResponse unreadCountResponse = UnreadCountResponse.builder()
                .count(notificationService.getUnReadNotificationCount(userId)).build();
        return ResponseEntity.ok(ApiResponse.success(unreadCountResponse));
    }

}