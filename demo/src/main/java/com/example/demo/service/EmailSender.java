package com.example.demo.service;
import com.example.demo.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailSender implements NotificationSender {
    public String getChannelType() { return "EMAIL"; }
    public void send(NotificationRequest request) {
        System.out.println("Sending EMAIL to User " + request.getUserId() + ": " + request.getTitle());
    }
}