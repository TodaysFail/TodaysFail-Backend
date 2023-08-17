package com.todaysfail.common.exception;

public class ExpiredTokenException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(GlobalErrorCode.TOKEN_EXPIRED);
    }
}
