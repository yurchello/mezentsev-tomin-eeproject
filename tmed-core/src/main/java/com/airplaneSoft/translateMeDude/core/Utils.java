package com.airplaneSoft.translateMeDude.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Mezentsev.Y on 9/30/2016.
 */
public class Utils {
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
