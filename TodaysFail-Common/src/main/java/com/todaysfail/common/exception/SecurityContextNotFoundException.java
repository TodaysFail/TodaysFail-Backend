package com.todaysfail.common.exception;

public class SecurityContextNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new SecurityContextNotFoundException();

    public SecurityContextNotFoundException() {
        super(GlobalErrorCode.SECURITY_CONTEXT_NOT_FOUND);
    }
}
