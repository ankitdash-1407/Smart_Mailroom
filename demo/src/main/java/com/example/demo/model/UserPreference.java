package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user_preferences")
public class UserPreference {
    @Id
    private Long userId;
    private boolean emailOptIn;
    private boolean smsOptIn;
    private boolean pushOptIn;
    private boolean inAppOptIn;

    // Getters needed for our routing logic
    public boolean isEmailOptIn() { return emailOptIn; }
    public boolean isSmsOptIn() { return smsOptIn; }
    public boolean isPushOptIn() { return pushOptIn; }
    public boolean isInAppOptIn() { return inAppOptIn; }
}
