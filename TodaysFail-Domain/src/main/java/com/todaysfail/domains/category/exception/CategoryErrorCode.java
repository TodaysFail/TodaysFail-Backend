package com.todaysfail.domains.category.exception;

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
public enum CategoryErrorCode implements BaseErrorCode {
    @ExplainError("카테고리를 찾을 수 없습니다.")
    CATEGORY_NOT_FOUND(BAD_REQUEST, "CATEGORY_400_1", "카테고리를 찾을 수 없습니다."),

    @ExplainError("본인이 생성한 카테고리가 아닙니다.")
    CATEGORY_NOT_OWNED_BY_USER(BAD_REQUEST, "CATEGORY_400_2", "본인이 생성한 카테고리가 아닙니다."),

    @ExplainError("카테고리 색상을 찾을 수 없습니다.")
    CATEGORY_COLOR_NOT_FOUND(BAD_REQUEST, "CATEGORY_COLOR_400_1", "카테고리 색상을 찾을 수 없습니다."),
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
