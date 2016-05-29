package com.mezentsev_tomin.adminpanel.exceptions;

/**
 * Created by Yuriy on 30.05.2016.
 */
public class InvalidUserInputException extends Exception {
    private static final long serialVersionUID = 4286728050862639524L;


    public InvalidUserInputException(String message) {
        super(message);
    }

    public InvalidUserInputException(Throwable cause) {
        super(cause);
    }

    public InvalidUserInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
