package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * This controller error handling
 */
//@Controller
@ControllerAdvice
public class ErrorsController extends BaseController{

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        logger.error("NoHandlerFoundException: ",ex);
        return "page404";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleEx(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", ex.getMessage());
        mav.setViewName("errorOccurred");
        logger.error("ResourceNotFoundException: ",ex);
        return mav;
    }
}
