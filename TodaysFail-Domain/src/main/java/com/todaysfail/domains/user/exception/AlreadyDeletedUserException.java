package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class AlreadyDeletedUserException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new AlreadyDeletedUserException();

    public AlreadyDeletedUserException() {
        super(UserErrorCode.USER_ALREADY_DELETED);
    }
}
