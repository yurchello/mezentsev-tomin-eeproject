package com.mezentsev_tomin.adminpanel.controller;

import com.mezentsev_tomin.adminpanel.service.UserService;
import com.mezentsev_tomin.adminpanel.utils.AAAA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	private MailSender mailSender;


//	@RequestMapping(value = { "/"}, method = RequestMethod.GET)
//	public String homePage(ModelMap model) {
//		return "home2";
//
//	}

	@RequestMapping(value = { "/products"}, method = RequestMethod.GET)
	public String productsPage(ModelMap model) {
		return "products";
	}

	@RequestMapping(value = { "/products"}, method = RequestMethod.POST)
	public String productsPagePost() {
		return "contactus";
	}

	@RequestMapping(value = { "/contactus"}, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {

//		model.addAttribute("mailFrom", getPrincipal());
		return "contactus";
	}

	@RequestMapping(value = { "/sendEmail"}, method = RequestMethod.POST)
	public String sendEmail(BindingResult result, ModelMap model,  HttpServletRequest request){
		System.out.println();
		return "contactus";
	}
//	@RequestMapping(value = { "/sendEmail"}, method = RequestMethod.POST)
//	public String sendEmail(ModelMap model, HttpServletRequest request, HttpServletResponse response){
//		EmailSender emailSender = new EmailSender(this.mailSender);
//		emailSender.setSubject("Test");
//		emailSender.setTo("tomin.mezentsev.examples@gmai.com");
//		emailSender.setMessageContent("HI!!!!!!!");
//		emailSender.setFrom("yura_guitar@list.ru");
//		emailSender.sendEmail();
//		return "";
//	}

	@RequestMapping(value = { "/download"}, method = RequestMethod.GET)
	public String cdownloadPage(ModelMap model) {
		return "download";
	}

	@RequestMapping(value = { "/help"}, method = RequestMethod.GET)
	public String helpPage(ModelMap model) {
		return "help";
	}

	@RequestMapping(value = { "/about"}, method = RequestMethod.GET)
	public String aboutPage(ModelMap model) {
		return "about";
	}
}