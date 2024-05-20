package com.company_management.controller.websoket;

import com.company_management.common.ResultResp;
import com.company_management.model.response.Notifications;
import com.company_management.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    private final SimpMessagingTemplate template;

    private Notifications notifications = new Notifications(0);

    @MessageMapping("/send")
    @SendTo("/topic/notifications")
    public ResultResp<Object> sendGlobalNotification(@RequestParam String message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendGlobalNotification(message);
        return ResultResp.success(null);
    }

    @MessageMapping("/private-message")
    @SendTo("/topic/private-messages")
    public ResultResp<Object> sendPrivateNotification(final Principal principal, @RequestParam String message) throws InterruptedException {
        Thread.sleep(1000);
        notificationService.sendPrivateNotification(principal.getName(), message);
        return ResultResp.success(null);
    }

    @GetMapping("/notify")
    public String getNotification() {
        // Increment Notification by one
        notifications.increment();
        // Push notifications to front-end
        template.convertAndSend("/topic/notification", notifications);
        return "Notifications successfully sent to Angular !";
    }


}
