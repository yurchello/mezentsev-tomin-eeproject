package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created by Mezentsev.Y on 9/24/2016.
 */
//@Controller
@ControllerAdvice
public class ErrorsController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "page404";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleEx(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", ex.getMessage());
        mav.setViewName("errorOccurred");
        return mav;
    }
}
