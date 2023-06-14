package com.vegetablemart.exceptions;

public class LoginExceptions extends RuntimeException {
    public LoginExceptions() {
    }

    public LoginExceptions(String message) {
        super(message);
    }
}
