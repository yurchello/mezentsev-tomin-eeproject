package com.airplaneSoft.translateMeDude.exceptions;

/**
 * This Exception wil be generated if some resources not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    private String msg;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}