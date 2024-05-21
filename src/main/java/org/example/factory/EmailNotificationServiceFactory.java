package org.example.factory;

import org.example.service.EmailNotificationService;
import org.example.service.INotificationService;

public class EmailNotificationServiceFactory implements INotificationServiceFactory {
    private final String emailUsername;
    private final String emailPassword;

    public EmailNotificationServiceFactory(String emailUsername, String emailPassword) {
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
    }
    @Override
    public INotificationService createNotificationService() {
        return new EmailNotificationService(emailUsername,emailPassword);
    }
}

