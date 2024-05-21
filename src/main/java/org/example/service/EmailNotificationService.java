package org.example.service;

import org.example.entity.User;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailNotificationService implements INotificationService {
    private final String emailUsername;
    private final String emailPassword;

    public EmailNotificationService(String emailUsername, String emailPassword) {
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
    }

    @Override
    public void sendNotification(User user, String message) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailUsername, emailPassword);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(emailUsername));
            emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            emailMessage.setSubject("Notification");
            emailMessage.setText(message);

            // Send the email
            Transport.send(emailMessage);
            System.out.println("Email successfully sent to " + user.getEmail() + ": " + message);
        } catch (MessagingException e) {
            System.err.println("Error in sending email to " + user.getEmail() + ": " + e.getMessage());
        }
    }
}
