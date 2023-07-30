package com.todaysfail.common.exception;

public class NotAvailableRedissonLockException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new NotAvailableRedissonLockException();

    private NotAvailableRedissonLockException() {
        super(GlobalErrorCode.NOT_AVAILABLE_REDISSON_LOCK);
    }
}
