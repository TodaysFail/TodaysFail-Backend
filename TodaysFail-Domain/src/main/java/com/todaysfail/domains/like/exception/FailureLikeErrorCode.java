package com.todaysfail.domains.like.exception;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.annotation.ExplainError;
import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.exception.BaseErrorCode;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FailureLikeErrorCode implements BaseErrorCode {
    @ExplainError("이미 좋아요 한 실패 게시물인 경우")
    FAILURE_LIKE_ALREADY_LIKED(BAD_REQUEST, "FAILURE_LIKE_400_1", "이미 좋아요 한 실패 게시물인 경우"),
    ;

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().reason(reason).code(code).status(status).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
