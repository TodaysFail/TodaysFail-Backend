package com.todaysfail.domains.user.exception;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements BaseErrorCode {
    USER_ALREADY_SIGNUP(BAD_REQUEST, "USER_400_1", "이미 회원가입한 유저입니다."),
    NICKNAME_GENERATION_FAILED(CONFLICT, "USER_409_1", "닉네임 생성에 실패했습니다."),
    ALREADY_USER_NAME(CONFLICT, "USER_409_2", "이미 등록된 닉네임입니다."),
    ;

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }
}
