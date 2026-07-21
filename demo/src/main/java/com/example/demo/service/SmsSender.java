package com.example.demo.service;
import com.example.demo.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class SmsSender implements NotificationSender {
    public String getChannelType() { return "SMS"; }
    public void send(NotificationRequest request) {
        System.out.println("Sending SMS to User " + request.getUserId() + ": " + request.getTitle());
    }
}
