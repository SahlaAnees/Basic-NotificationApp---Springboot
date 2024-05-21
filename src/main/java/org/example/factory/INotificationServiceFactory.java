package org.example.factory;

import org.example.service.INotificationService;

public interface INotificationServiceFactory {
    INotificationService createNotificationService();
}
