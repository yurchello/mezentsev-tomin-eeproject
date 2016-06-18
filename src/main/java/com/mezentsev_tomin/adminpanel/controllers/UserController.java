package com.mezentsev_tomin.adminpanel.controllers;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.exceptions.InvalidUserInputException;
import com.mezentsev_tomin.adminpanel.forms.LoginForm;
import com.mezentsev_tomin.adminpanel.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by Yuriy on 05.06.2016.
 */
//@Controller
public class UserController {
    @Autowired
    private UserService userService;

//    @RequestMapping(value = "/hello", method =  RequestMethod.GET)
//    public String helloWorld(ModelMap modelMap){
//          modelMap.addAttribute("message", "HELLO!!!");
//          return "hello";
//    }


//    @RequestMapping(value = "/")
//    public String home() {
//        System.out.println();
//        return "redirect:/login";
//    }

//    @RequestMapping(value ="/login", method=RequestMethod.GET)
//    public String showLogin(Model model){
//       model.addAttribute("loginForm",new LoginForm());
//        return "login";
//    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ModelAndView login(	@RequestParam(value = "error", required = false) String error,
//                                  @RequestParam(value = "logout", required = false) String logout){
//        ModelAndView model = new ModelAndView();
//        if (error != null) {
//            model.addObject("error", "Invalid username and password!");
//        }
//
//        if (logout != null) {
//            model.addObject("msg", "You've been logged out successfully.");
//        }
//        model.setViewName("login");
//
//        return model;
//    }

    @RequestMapping(value = "/login", method =  RequestMethod.POST)
    public String helloWorld(ModelMap modelMap){
          modelMap.addAttribute("message", "HELLO!!!");
          return "hello";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap modelMap){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register2(ModelMap modelMap){
        return "register";
    }

//    @RequestMapping(value="/login", method=RequestMethod.POST)
//    public String login(@ModelAttribute("loginForm") LoginForm loginForm,
//                        Model model, HttpSession session){
//
//        try {
//            User user = new User(666666L,
//                    "User666",
//                    "user666",
//                    "666@.gmail.com",
//                    "1111",
//                    "/photo/p.jpg",
//                    "someinfo" );
//            session.setAttribute("CURRENT_ACCOUNT", user);
//            return "login_ok";
//        } catch (RuntimeException e) {
//            return "login_not_ok";
//        }
//    }


    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLogin(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

}
