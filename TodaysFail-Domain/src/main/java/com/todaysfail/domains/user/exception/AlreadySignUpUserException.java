package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class AlreadySignUpUserException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new AlreadySignUpUserException();

    public AlreadySignUpUserException() {
        super(UserErrorCode.USER_ALREADY_SIGNUP);
    }
}
