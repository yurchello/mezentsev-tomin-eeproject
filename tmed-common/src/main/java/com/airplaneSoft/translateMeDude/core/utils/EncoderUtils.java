package com.airplaneSoft.translateMeDude.core.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by Mezentsev.Y on 2/9/2017.
 */
public class EncoderUtils {
    /**
     * Provide password encryption for User entity
     * @return
     */
    public static PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
