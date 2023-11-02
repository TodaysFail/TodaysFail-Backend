package com.todaysfail.domains.failure.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class FailureNotOwnedByUserException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new FailureNotOwnedByUserException();

    public FailureNotOwnedByUserException() {
        super(FailureErrorCode.FAILURE_NOT_OWNED_BY_USER);
    }
}
