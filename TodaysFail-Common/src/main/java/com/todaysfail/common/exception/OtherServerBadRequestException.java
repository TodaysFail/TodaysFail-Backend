package com.todaysfail.common.exception;

public class OtherServerBadRequestException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
