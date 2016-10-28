package com.airplaneSoft.translateMeDude.controller;

import com.airplaneSoft.translateMeDude.service.UserProfileService;
import com.airplaneSoft.translateMeDude.service.UserService;
import com.airplaneSoft.translateMeDude.service.WordGroupService;
import com.airplaneSoft.translateMeDude.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * This controller provide base functionality of other controllers
 */
@Controller
public class BaseController {
    static final Logger logger = LoggerFactory.getLogger(MainController.class);
    protected static String UPLOAD_LOCATION="C:/aaa/";

    @Autowired
    UserService userService;
    @Autowired
    WordGroupService wordGroupService;

    @Autowired
    WordService wordService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
    protected boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

    /**
     *This method returns the SSOID of authenticated user
     */
    protected String getSSOIdifAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    protected String getPrincipal(){
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    //// TODO: 10/28/2016 remove
    protected boolean isLoggedInUser(){
        return !"anonymousUser".equals(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @ModelAttribute("loggedinuser")
	public String initLoggedinuser(){
		return getPrincipal();
	}
}
