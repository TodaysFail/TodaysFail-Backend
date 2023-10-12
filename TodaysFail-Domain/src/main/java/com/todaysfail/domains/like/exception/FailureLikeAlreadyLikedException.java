package com.todaysfail.domains.like.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class FailureLikeAlreadyLikedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new FailureLikeAlreadyLikedException();

    public FailureLikeAlreadyLikedException() {
        super(FailureLikeErrorCode.FAILURE_LIKE_ALREADY_LIKED);
    }
}
