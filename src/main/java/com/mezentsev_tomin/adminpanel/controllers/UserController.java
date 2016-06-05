package com.mezentsev_tomin.adminpanel.controllers;

import com.mezentsev_tomin.adminpanel.forms.LoginForm;
import com.mezentsev_tomin.adminpanel.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yuriy on 05.06.2016.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method =  RequestMethod.GET)
    public String helloWorld(ModelMap modelMap){
          modelMap.addAttribute("message", "HELLO!!!");
          return "hello";
    }


    @RequestMapping(value = "/")
    public String home() {
        System.out.println();
        return "redirect:/login";
    }

    @RequestMapping(value ="/login", method=RequestMethod.GET)
    public String showLogin(Model model){
       model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") LoginForm loginForm, Model model){
        System.out.println();
        return "hello";
    }


}
