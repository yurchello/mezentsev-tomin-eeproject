package com.airplaneSoft.translateMeDude.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class AppController extends BaseController{

	@RequestMapping(value = { "/help"}, method = RequestMethod.GET)
	public String helpPage(ModelMap model) {
		return "help";
	}

	@RequestMapping(value = { "/about"}, method = RequestMethod.GET)
	public String aboutPage(ModelMap model) {
		return "about";
	}



}