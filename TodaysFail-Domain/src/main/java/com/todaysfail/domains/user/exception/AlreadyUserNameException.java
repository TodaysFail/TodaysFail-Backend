package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class AlreadyUserNameException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new AlreadyUserNameException();

    public AlreadyUserNameException() {
        super(UserErrorCode.ALREADY_USER_NAME);
    }
}
