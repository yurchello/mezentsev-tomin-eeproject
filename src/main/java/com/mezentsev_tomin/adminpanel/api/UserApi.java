package com.mezentsev_tomin.adminpanel.api;

import com.mezentsev_tomin.adminpanel.beans.User;
import com.mezentsev_tomin.adminpanel.servise.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mezentsev.Y on 5/19/2016.
 */
@Controller
public class UserApi {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user){
        this.userService.create(user);
        return "";
    }

}
