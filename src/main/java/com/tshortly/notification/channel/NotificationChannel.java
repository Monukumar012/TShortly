package com.tshortly.notification.channel;

import com.tshortly.notification.entity.NotificationMessage;

public interface NotificationChannel {
    void send(NotificationMessage message);
}