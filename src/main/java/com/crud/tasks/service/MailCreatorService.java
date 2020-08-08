package com.crud.tasks.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private final TemplateEngine templateEngine;

    public MailCreatorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String buildTrelloCardEmail(String message){
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("trello.mail/created-trello-card-mail", context);
    }

}
