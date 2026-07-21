package com.example.demo.service;
import com.example.demo.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class InAppSender implements NotificationSender {
    public String getChannelType() { return "IN_APP"; }
    public void send(NotificationRequest request) {
        System.out.println("Sending IN-APP to User " + request.getUserId() + ": " + request.getTitle());
    }
}
