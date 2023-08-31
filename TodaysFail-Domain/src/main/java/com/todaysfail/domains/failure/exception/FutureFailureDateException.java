package com.todaysfail.domains.failure.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class FutureFailureDateException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new FutureFailureDateException();

    public FutureFailureDateException() {
        super(FailureErrorCode.FAILURE_DATE_IS_FUTURE);
    }
}
