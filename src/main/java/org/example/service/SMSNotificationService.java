package org.example.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.example.entity.User;

public class SMSNotificationService implements INotificationService {
    private final String twilioPhoneNumber;

    public SMSNotificationService(String twilioAccountSid, String twilioAuthToken, String twilioPhoneNumber) {
        this.twilioPhoneNumber = twilioPhoneNumber;

        Twilio.init(twilioAccountSid, twilioAuthToken);
    }

    @Override
    public void sendNotification(User user, String message) {
        String toPhoneNumber = user.getPhoneNumber();

        try {
            Message smsMessage = Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(twilioPhoneNumber),
                    message
            ).create();

            System.out.println("SMS successfully sent to " + toPhoneNumber + ": " + smsMessage.getBody());
        } catch (Exception e) {
            System.err.println("Error in sending SMS:");
            e.printStackTrace();
        }
    }
}

