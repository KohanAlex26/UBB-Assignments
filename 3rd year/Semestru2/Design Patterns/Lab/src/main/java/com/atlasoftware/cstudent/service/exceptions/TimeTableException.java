package com.atlasoftware.cstudent.service.exceptions;

public class TimeTableException extends Exception{
    public TimeTableException(String message) {
        super(message);
    }

    public TimeTableException(Throwable t) {
        super(t);
    }

    public TimeTableException(String message, Throwable t) {
        super(message, t);
    }
}