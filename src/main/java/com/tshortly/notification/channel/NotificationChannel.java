package com.tshortly.notification.channel;

import com.tshortly.notification.model.NotificationMessage;

public interface NotificationChannel {
    void send(NotificationMessage message);
}