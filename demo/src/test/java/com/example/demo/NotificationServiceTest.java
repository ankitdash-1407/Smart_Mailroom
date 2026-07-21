package com.example.demo;

import com.example.demo.dto.NotificationRequest;
import com.example.demo.model.UserPreference;
import com.example.demo.repository.NotificationHistoryRepository;
import com.example.demo.repository.UserPreferenceRepository;
import com.example.demo.service.EmailSender;
import com.example.demo.service.NotificationService;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NotificationServiceTest {

    @Test
    void testNotificationRoutingAndOptOut() {
        UserPreferenceRepository prefRepo = mock(UserPreferenceRepository.class);
        NotificationHistoryRepository histRepo = mock(NotificationHistoryRepository.class);
        EmailSender emailSender = mock(EmailSender.class);
        
        when(emailSender.getChannelType()).thenReturn("EMAIL");
        
        NotificationService service = new NotificationService(prefRepo, histRepo, List.of(emailSender));

        // Mock a user who opted OUT of Emails
        UserPreference mockPref = mock(UserPreference.class);
        when(mockPref.isEmailOptIn()).thenReturn(false); 
        when(prefRepo.findById(1L)).thenReturn(Optional.of(mockPref));

        // Create a payload requesting an Email
        NotificationRequest request = new NotificationRequest();
        request.setUserId(1L);
        request.setChannels(List.of("EMAIL"));

        // Run the service
        service.processNotification(request);

        // Prove the filter works: the email sender was NEVER triggered because of the opt-out rule
        verify(emailSender, never()).send(any()); 
    }
}