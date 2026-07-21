package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notification_history")
public class NotificationHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String channel;
    private String status;
    private LocalDateTime createdAt = LocalDateTime.now();

    public NotificationHistory() {} // Default constructor for JPA
    
    public NotificationHistory(Long userId, String channel, String status) {
        this.userId = userId;
        this.channel = channel;
        this.status = status;
    }
}
