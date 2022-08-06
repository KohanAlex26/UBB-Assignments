package com.atlasoftware.cstudent.service.exceptions;

public class LoginException extends Exception{

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable t) {
        super(t);
    }

    public LoginException(String message, Throwable t) {
        super(message, t);
    }
}
