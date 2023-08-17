package com.todaysfail.common.exception;

public class InvalidTokenException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
