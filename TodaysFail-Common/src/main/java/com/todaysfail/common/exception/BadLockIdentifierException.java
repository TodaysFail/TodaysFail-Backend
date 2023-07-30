package com.todaysfail.common.exception;

public class BadLockIdentifierException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new BadLockIdentifierException();

    private BadLockIdentifierException() {
        super(GlobalErrorCode.BAD_LOCK_IDENTIFIER);
    }
}
