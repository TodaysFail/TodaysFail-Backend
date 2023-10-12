package com.todaysfail.domains.failure.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class FailureNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new FailureNotFoundException();

    public FailureNotFoundException() {
        super(FailureErrorCode.FAILURE_NOT_FOUND);
    }
}
