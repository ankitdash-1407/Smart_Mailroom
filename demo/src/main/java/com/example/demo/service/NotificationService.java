package com.example.demo.service;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.model.NotificationHistory;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.NotificationHistoryRepository;
import com.example.demo.repository.UserPreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final UserPreferenceRepository preferenceRepo;
    private final NotificationHistoryRepository historyRepo;
    private final Map<String, NotificationSender> senderMap;

    @Autowired
    public NotificationService(UserPreferenceRepository preferenceRepo, 
                               NotificationHistoryRepository historyRepo, 
                               List<NotificationSender> senders) {
        this.preferenceRepo = preferenceRepo;
        this.historyRepo = historyRepo;
        // Maps the channel type (e.g., "EMAIL") to the correct sender class automatically
        this.senderMap = senders.stream()
                .collect(Collectors.toMap(NotificationSender::getChannelType, sender -> sender));
    }

    public void processNotification(NotificationRequest request) {
        Long userId = request.getUserId();
        
        // 1. Fetch user preferences. If not found, assume opted out of everything.
        UserPreference prefs = preferenceRepo.findById(userId).orElse(null);
        if (prefs == null) {
            System.out.println("User preferences not found for ID: " + userId);
            return;
        }

        // 2. Loop through requested channels
        for (String channel : request.getChannels()) {
            channel = channel.toUpperCase();
            
            // 3. Check opt-in status
            boolean isOptedIn = checkOptIn(prefs, channel);

            if (isOptedIn && senderMap.containsKey(channel)) {
                // Send and Log Success
                senderMap.get(channel).send(request);
                logHistory(userId, channel, "SUCCESS");
            } else {
                // Log Failed/Skipped due to preference
                logHistory(userId, channel, "SKIPPED_OPT_OUT");
            }
        }
    }

    private boolean checkOptIn(UserPreference prefs, String channel) {
        return switch (channel) {
            case "EMAIL" -> prefs.isEmailOptIn();
            case "SMS" -> prefs.isSmsOptIn();
            case "PUSH" -> prefs.isPushOptIn();
            case "IN_APP" -> prefs.isInAppOptIn();
            default -> false;
        };
    }

    private void logHistory(Long userId, String channel, String status) {
        NotificationHistory history = new NotificationHistory(userId, channel, status);
        historyRepo.save(history);
    }
}
