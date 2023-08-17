package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class NicknameGenerationFailedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new NicknameGenerationFailedException();

    public NicknameGenerationFailedException() {
        super(UserErrorCode.NICKNAME_GENERATION_FAILED);
    }
}
