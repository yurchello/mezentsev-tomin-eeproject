package com.mezentsev_tomin.adminpanel.controllers;

import com.mezentsev_tomin.adminpanel.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yuriy on 05.06.2016.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello")
    public String helloWorld(ModelMap modelMap){
          modelMap.addAttribute("message", "HELLO!!!");
          return "hello";
    }
}
