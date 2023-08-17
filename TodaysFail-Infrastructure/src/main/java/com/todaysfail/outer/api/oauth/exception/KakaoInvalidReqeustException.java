package com.todaysfail.outer.api.oauth.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class KakaoInvalidReqeustException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new KakaoInvalidReqeustException();

    public KakaoInvalidReqeustException() {
        super(KakaoOauthErrorCode.KOE_INVALID_REQUEST);
    }
}
