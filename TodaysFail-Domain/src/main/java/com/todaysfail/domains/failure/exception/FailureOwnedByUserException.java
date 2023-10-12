package com.todaysfail.domains.failure.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class FailureOwnedByUserException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new FailureOwnedByUserException();

    public FailureOwnedByUserException() {
        super(FailureErrorCode.FAILURE_OWNED_BY_USER);
    }
}
