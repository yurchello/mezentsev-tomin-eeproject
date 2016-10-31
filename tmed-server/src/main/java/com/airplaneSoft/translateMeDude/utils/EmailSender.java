package com.airplaneSoft.translateMeDude.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * This class  provide sending email by specific credentials
 */

public class EmailSender {

    private String messageContent;
    private String from = "";
    private String to;
    private String subject;

    private MailSender mailSender;

    public EmailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageContent + " from email " + from);
        mailSender.send(message);
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
