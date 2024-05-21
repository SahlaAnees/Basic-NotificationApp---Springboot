package org.example.factory;

import org.example.service.INotificationService;
import org.example.service.SMSNotificationService;

public class SMSNotificationServiceFactory implements INotificationServiceFactory{
    private final String twilioAccountSid;
    private final String twilioAuthToken;
    private final String twilioPhoneNumber;

    public SMSNotificationServiceFactory(String twilioAccountSid, String twilioAuthToken, String twilioPhoneNumber) {
        this.twilioAccountSid = twilioAccountSid;
        this.twilioAuthToken = twilioAuthToken;
        this.twilioPhoneNumber = twilioPhoneNumber;
    }

    @Override
    public INotificationService createNotificationService() {
        return new SMSNotificationService(twilioAccountSid,twilioAuthToken,twilioPhoneNumber);
    }
}
