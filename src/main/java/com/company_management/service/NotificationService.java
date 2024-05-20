package com.company_management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendGlobalNotification(String message) {
        simpMessagingTemplate.convertAndSend("/topic/global", message);
    }

    public void sendPrivateNotification(final String userName, String message) {
        simpMessagingTemplate.convertAndSendToUser(userName, "/topic/private", message);
    }
}
