package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Autowired
    public EmailScheduler(SimpleEmailService simpleEmailService, TaskRepository taskRepository, AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.adminConfig = adminConfig;
    }

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        String task = (size==1)?"task":"tasks";
        String content = "Currently in database you got: " + size + " " + task;
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                content), 0);
    }

    @Scheduled(cron = "0 */5 * * * *")
    public void sendInformationEmailDaily() {
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                ""), 2);
    }

}
