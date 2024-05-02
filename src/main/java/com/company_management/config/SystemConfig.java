package com.company_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class SystemConfig {

    @Bean("taskName")
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor bean = new ThreadPoolTaskExecutor();
        bean.setCorePoolSize(5);
        bean.setMaxPoolSize(50);
        bean.setKeepAliveSeconds(1);
        bean.setQueueCapacity(200);
        bean.setWaitForTasksToCompleteOnShutdown(true);
        bean.setRejectedExecutionHandler(new CallerRunsPolicy());
        return bean;
    }

    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAwareImpl();
    }

}
