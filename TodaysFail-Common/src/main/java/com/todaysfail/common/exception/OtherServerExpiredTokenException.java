package com.todaysfail.common.exception;

public class OtherServerExpiredTokenException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
