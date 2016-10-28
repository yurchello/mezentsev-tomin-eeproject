package com.airplaneSoft.translateMeDude.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class
 */
public class Utils {
    /**
     * Provide password encryption for User entity
     * @return
     */
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
