package com.mezentsev_tomin.adminpanel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Mezentsev.Y on 9/24/2016.
 */
@Controller
public class UtilsController {
    @RequestMapping(value = { "/errorOccurred" }, method = RequestMethod.GET)
    public String errorShow(ModelMap model){
        return "errorOccurred";
    }

}
