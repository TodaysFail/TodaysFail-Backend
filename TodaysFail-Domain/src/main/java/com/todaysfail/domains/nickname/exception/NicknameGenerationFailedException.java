package com.todaysfail.domains.nickname.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class NicknameGenerationFailedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new NicknameGenerationFailedException();

    public NicknameGenerationFailedException() {
        super(NicknameErrorCode.NICKNAME_GENERATION_FAILED);
    }
}
