package com.atlasoftware.cstudent.service.exceptions;

public class StudentRegistrationException extends Exception {

    public StudentRegistrationException(String message) {
        super(message);
    }

    public StudentRegistrationException(Throwable t) {
        super(t);
    }

    public StudentRegistrationException(String message, Throwable t) {
        super(message, t);
    }
}