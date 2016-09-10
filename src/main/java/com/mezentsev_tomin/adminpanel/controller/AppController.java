package com.mezentsev_tomin.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class AppController {

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
		return "contactus";
	}

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