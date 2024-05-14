package com.company_management.config;

import com.company_management.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
@Slf4j
public class ScheduledTasks {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final EmployeeService employeeService;

    public ScheduledTasks(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Scheduled(cron = "0 0 6,23 * * *")
    public void updateEmployeeStatusTask() {
        log.info("Update lại isActive userDetail");
        employeeService.updateEmployeeStatus();
    }
}
