package com.company_management.controller.websoket;

import com.company_management.model.entity.UserDetail;
import com.company_management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {


    private final EmployeeService employeeService;

    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "0 0 8 * * ?")
    public void sendBirthdayNotifications() {
        List<UserDetail> employees = employeeService.getTodayBirthdayEmployees();
        if (!employees.isEmpty()) {
            messagingTemplate.convertAndSend("/topic/birthdays", employees);
        }
    }

    @GetMapping("/birthdays")
    public List<UserDetail> getTodayBirthdayEmployees() {
        return employeeService.getTodayBirthdayEmployees();
    }

    @GetMapping("/birthdays/month")
    public List<UserDetail> getEmployeesWithBirthdaysInCurrentMonth() {
        return employeeService.getEmployeesWithBirthdaysInCurrentMonth();
    }

}
