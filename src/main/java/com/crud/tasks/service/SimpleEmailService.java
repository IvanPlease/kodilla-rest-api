package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public SimpleEmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(final Mail mail){
        log.info("Starting email preparation...");
        try{
            SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(mailMessage);
            log.info("Email has been sent.");
        }catch (MailException e){
            log.error("Failed to process email sending: {}", e.getMessage());
        }
    }

    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc() != null){
            mailMessage.setCc(mail.getToCc());
        }
        return mailMessage;
    }
}
