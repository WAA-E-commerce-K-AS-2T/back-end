package com.spa.ecommerce.notificationService;

import org.springframework.stereotype.Service;


@Service
public class MockServiceEmail {

    public void sendSimpleMessage(String to, String subject, String text) {
        // Simulate sending email by printing to console
        System.out.println("*************************************");
        System.out.println("** Sending email to: " + to);
        System.out.println("** Subject: " + subject);
        System.out.println("** Text: " + text);
        System.out.println("*************************************");
    }
}

