package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    private final AdminConfig adminConfig;
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private static final String SUBJECT = "Task: New Trello card";

    public TrelloService(AdminConfig adminConfig, TrelloClient trelloClient, SimpleEmailService emailService) {
        this.adminConfig = adminConfig;
        this.trelloClient = trelloClient;
        this.emailService = emailService;
    }

    public List<TrelloBoardDto> fetchTrelloBoards(){
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createdTrelloCard(final TrelloCardDto trelloCard){
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCard);
        ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT,
                "New card: " + trelloCard.getName() + " has been created on your Trello account"), false));
        return newCard;
    }
}
