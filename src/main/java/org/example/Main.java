package org.example;

import org.example.entity.User;
import org.example.sender.NotificationSender;
import org.example.service.EmailNotificationService;
import org.example.service.SMSNotificationService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load configuration properties.");
            e.printStackTrace();
            return;
        }

        String emailUsername = properties.getProperty("email.username");
        String emailPassword = properties.getProperty("email.password");

        String twilioAccountSid = properties.getProperty("twilio.account.sid");
        String twilioAuthToken = properties.getProperty("twilio.auth.token");
        String twilioPhoneNumber = properties.getProperty("twilio.phone.number");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user name: ");
        String userName = scanner.nextLine();

        System.out.print("Enter user email: ");
        String userEmail = scanner.nextLine();

        System.out.print("Enter user phone number: ");
        String userPhoneNumber = scanner.nextLine();

        User user = new User(userName, userEmail, userPhoneNumber);

        System.out.print("Select notification method (1 for Email, 2 for SMS, 3 for Both): ");
        int choice = scanner.nextInt();

        String emailMessage = "Hi " + user.getUsername() + ", this is an email notification...!";
        String smsMessage = "Hi " + user.getUsername() + ", this is an SMS notification...!";

        NotificationSender notificationSender = new NotificationSender();

        if (choice == 1 || choice == 3) {
            EmailNotificationService emailNotificationService = new EmailNotificationService(emailUsername, emailPassword);
            notificationSender.addNotificationService(emailNotificationService);
            notificationSender.sendNotification(user, emailMessage);
        }

        if (choice == 2 || choice == 3) {
            SMSNotificationService smsNotificationService = new SMSNotificationService(twilioAccountSid, twilioAuthToken, twilioPhoneNumber);
            notificationSender.addNotificationService(smsNotificationService);
            notificationSender.sendNotification(user, smsMessage);
        }
    }
}
