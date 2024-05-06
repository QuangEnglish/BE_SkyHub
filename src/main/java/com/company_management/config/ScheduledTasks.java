package com.company_management.config;

import com.company_management.service.EmployeeService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final EmployeeService employeeService;

    public ScheduledTasks(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "0 0 18,8,23 * * *") // Chạy vào lúc 18:00:00 hàng ngày
    public void updateEmployeeStatusTask() {
        employeeService.updateEmployeeStatus();
    }
}
