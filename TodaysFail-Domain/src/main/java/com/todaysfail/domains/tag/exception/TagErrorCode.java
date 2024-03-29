package com.todaysfail.domains.tag.exception;

import com.todaysfail.common.annotation.ExplainError;
import com.todaysfail.common.consts.TodaysFailConst;
import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.exception.BaseErrorCode;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TagErrorCode implements BaseErrorCode {
    @ExplainError("허용된 최대 태그 개수를 초과했습니다.")
    TAG_COUNT_EXCEED(TodaysFailConst.BAD_REQUEST, "TAG_400_1", "허용된 최대 태그 개수를 초과했습니다."),

    @ExplainError("태그를 찾을 수 없습니다.")
    TAG_NOT_FOUND(TodaysFailConst.BAD_REQUEST, "TAG_400_2", "태그를 찾을 수 없습니다."),

    @ExplainError("태그 이름의 길이 제한을 초과했습니다.")
    TAG_NAME_LENGTH_EXCEED(TodaysFailConst.BAD_REQUEST, "TAG_400_3", "태그 이름의 길이 제한을 초과했습니다."),
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
