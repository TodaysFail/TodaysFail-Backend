package com.todaysfail.common.exception;

public class RefreshTokenExpiredException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new RefreshTokenExpiredException();

    private RefreshTokenExpiredException() {
        super(GlobalErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
