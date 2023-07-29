package com.todaysfail.common.exception;

public class OtherServerUnauthorizedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED);
    }
}
