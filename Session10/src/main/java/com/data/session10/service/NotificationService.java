package com.data.session10.service;

import com.data.session10.model.entity.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    List<Notification> getNotificationsByAccountId(UUID accountId);
    void sendNotification(UUID accountId, String content);
}
