package org.example.service;

import org.example.entity.User;

public interface INotificationService {
    void sendNotification(User user, String message);
}
