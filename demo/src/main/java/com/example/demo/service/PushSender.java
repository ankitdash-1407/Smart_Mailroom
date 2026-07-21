package com.example.demo.service;
import com.example.demo.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class PushSender implements NotificationSender {
    public String getChannelType() { return "PUSH"; }
    public void send(NotificationRequest request) {
        System.out.println("Sending PUSH to User " + request.getUserId() + ": " + request.getTitle());
    }
}
