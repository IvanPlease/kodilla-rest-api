package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    private final TemplateEngine templateEngine;
    private final DbService service;
    private final TaskMapper mapper;
    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;

    public MailCreatorService(TemplateEngine templateEngine, DbService service, TaskMapper mapper, AdminConfig adminConfig, CompanyConfig companyConfig) {
        this.templateEngine = templateEngine;
        this.service = service;
        this.mapper = mapper;
        this.adminConfig = adminConfig;
        this.companyConfig = companyConfig;
    }

    public String buildTrelloCardEmail(String message){
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("companyConfig", companyConfig);
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail.html", context);
    }

    public String buildDailyReportMail(){
        List<TaskDto> functionality = mapper.mapToTaskDtoList(service.getAllTasks());
        Context context = new Context();
        context.setVariable("companyConfig", companyConfig);
        context.setVariable("fetchedTasks", functionality);
        context.setVariable("taskExists", (functionality.size()>0));
        return templateEngine.process("mail/daily/new-mail-template.html", context);
    }

}
