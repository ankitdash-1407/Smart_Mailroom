package com.example.demo.service;
import com.example.demo.dto.NotificationRequest;

public interface NotificationSender {
    String getChannelType();
    void send(NotificationRequest request);
}
