package com.todaysfail.domains.failure.exception;

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
public enum FailureErrorCode implements BaseErrorCode {
    @ExplainError("실패 기록을 찾을 수 없을 때")
    FAILURE_NOT_FOUND(NOT_FOUND, "FAILURE_400_1", "실패를 찾을 수 없습니다."),

    @ExplainError("입력 한 날짜가 미래일 수 없습니다.")
    FAILURE_DATE_IS_FUTURE(NOT_FOUND, "FAILURE_400_2", "입력 한 날짜가 미래일 수 없습니다."),
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
