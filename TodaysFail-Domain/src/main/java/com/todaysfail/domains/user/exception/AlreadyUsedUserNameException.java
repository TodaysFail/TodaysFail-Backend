package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class AlreadyUsedUserNameException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new AlreadyUsedUserNameException();

    public AlreadyUsedUserNameException() {
        super(UserErrorCode.ALREADY_USER_NAME);
    }
}
