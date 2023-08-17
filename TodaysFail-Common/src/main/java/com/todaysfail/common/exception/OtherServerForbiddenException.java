package com.todaysfail.common.exception;

public class OtherServerForbiddenException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(GlobalErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
