package com.mezentsev_tomin.adminpanel.controllers;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.exceptions.InvalidUserInputException;
import com.mezentsev_tomin.adminpanel.forms.UserForm;
import com.mezentsev_tomin.adminpanel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Yuriy on 18.06.2016.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegister(Model model){
        model.addAttribute("registerForm", new UserForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView doRegister(Model model, @ModelAttribute("registerForm")UserForm form, BindingResult result){
        User user;
        try {
            user = userService.signUp(
                    form.getUsername(),
                    form.getEmail(),
                    form.getPassword(),
                    form.getPhotoPath(),
                    form.getDescription());
            //model.addAttribute("userForm", form);
            ModelAndView modelAndView = new ModelAndView("account");
            modelAndView.addObject("username",form.getUsername());
            modelAndView.addObject("password",form.getPassword());
            modelAndView.addObject("email",form.getEmail());
            modelAndView.addObject("description",form.getDescription());
            modelAndView.addObject("photoPath",form.getPhotoPath());
            return modelAndView;
        } catch (InvalidUserInputException e) {
            model.addAttribute("message", "Registration Error!");
            return new ModelAndView("registerationFail");
        }
    }

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLogin(Model model){
        //model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveAccount(Model model, @ModelAttribute("registerForm")UserForm form){
        User user;
        user = userService.findByLogin(form.getUsername());
        user.setEmail(form.getEmail());
        user.setDescription(form.getDescription());
        user.setPhoto_path(form.getPhotoPath());
        userService.update(user);
        return "account";
    }
}
