package org.example.sender;

import org.example.entity.User;
import org.example.service.INotificationService;

import java.util.ArrayList;
import java.util.List;

public class NotificationSender {
    private List<INotificationService> notificationServices = new ArrayList<>();

    public void addNotificationService(INotificationService notificationService) {
        notificationServices.add(notificationService);
    }

    public void sendNotification(User user, String message) {
        for (INotificationService service : notificationServices) {
            service.sendNotification(user, message);
        }
    }
}

