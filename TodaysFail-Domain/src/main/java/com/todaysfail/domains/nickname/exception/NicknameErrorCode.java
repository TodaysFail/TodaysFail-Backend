package com.todaysfail.domains.nickname.exception;

import static com.todaysfail.common.consts.TodaysFailConst.*;
import static com.todaysfail.common.exception.GlobalErrorCode.*;

import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.exception.BaseErrorCode;
import com.todaysfail.common.exception.GlobalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NicknameErrorCode implements BaseErrorCode {
    NICKNAME_GENERATION_FAILED(
            INTERNAL_SERVER_ERROR, "NICKNAME_GENERATION_FAILED", "닉네임 생성에 실패했습니다."),
    ;

    private Integer status;
    private String code;
    private String reason;

    NicknameErrorCode(
            GlobalErrorCode internalServerError, String nicknameGenerationFailed, String s) {}

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
