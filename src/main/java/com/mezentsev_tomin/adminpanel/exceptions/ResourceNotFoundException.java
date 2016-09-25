package com.mezentsev_tomin.adminpanel.exceptions;

/**
 * Created by Mezentsev.Y on 9/25/2016.
 */
public class ResourceNotFoundException extends RuntimeException {
    private String msg;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}