package com.airplaneSoft.translateMeDude.models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * This class is entity for contact page. Email send block.
 */
public class EmailSenderModel implements Serializable {
    @Email
    @NotEmpty
    private String mailFrom;
    @NotEmpty
    private String subject;
    @NotEmpty
    private String message;

    public EmailSenderModel(String mailFrom, String subject, String message) {
        this.mailFrom = mailFrom;
        this.subject = subject;
        this.message = message;
    }

    public EmailSenderModel() {
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
