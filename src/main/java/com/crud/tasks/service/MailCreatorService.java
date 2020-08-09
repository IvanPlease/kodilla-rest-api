package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private final TemplateEngine templateEngine;
    private final AdminConfig adminConfig;
    private final CompanyConfig companyConfig;

    public MailCreatorService(TemplateEngine templateEngine, AdminConfig adminConfig, CompanyConfig companyConfig) {
        this.templateEngine = templateEngine;
        this.adminConfig = adminConfig;
        this.companyConfig = companyConfig;
    }

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("task_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("admin_company", companyConfig.getCompanyName());
        context.setVariable("admin_address_street", companyConfig.getAppAddressStreet());
        context.setVariable("admin_address_number", companyConfig.getAppAddressNumber());
        context.setVariable("admin_mail", companyConfig.getCompanyEmail());
        return templateEngine.process("mail/created-trello-card-mail.html", context);
    }

}
