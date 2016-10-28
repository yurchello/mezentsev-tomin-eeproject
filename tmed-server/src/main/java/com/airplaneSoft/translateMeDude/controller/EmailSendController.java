package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.models.EmailSenderModel;
import com.airplaneSoft.translateMeDude.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * This controller provide a send email
 */
@Controller
@RequestMapping("/")
public class EmailSendController extends BaseController{

    static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private MailSender mailSender;

    /**
     *This method provide contact page to view
     */
    @RequestMapping(value = { "/contactus"}, method = RequestMethod.GET)
    public String contactUsPage(ModelMap model) {
        EmailSenderModel emailSenderModel = new EmailSenderModel();
        model.addAttribute("emailSenderModel", emailSenderModel);
        return "contactus";
    }

    /**
     *This method provide to get information from contact page and sed email
     */
    @RequestMapping(value = { "/contactus"}, method = RequestMethod.POST)
    public String sendEmail(@Valid EmailSenderModel emailSenderModel , BindingResult result, ModelMap model, HttpServletRequest request){
        if (result.hasErrors()){
            return "contactus";
        }else {
            EmailSender emailSender = new EmailSender(this.mailSender);
            emailSender.setSubject(emailSenderModel.getSubject());
            emailSender.setTo("tomin.mezentsev.examples@gmai.com");
            emailSender.setMessageContent(emailSenderModel.getMessage());
            emailSender.setFrom(emailSenderModel.getMailFrom());
            try {
                emailSender.sendEmail();
            }catch (RuntimeException e){
                logger.error("Email sent", e);
                request.setAttribute("message", "Error sending email: " + e.getMessage());
                return "errorSendingEmail";
            }
            logger.info("Email sent successfully");
            return "successSendingEmail";
        }
    }
}
