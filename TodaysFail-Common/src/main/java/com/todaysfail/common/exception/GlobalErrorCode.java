package com.todaysfail.common.exception;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.annotation.ExplainError;
import com.todaysfail.common.dto.ErrorReason;
import java.lang.reflect.Field;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    // 400 번대 에러
    @ExplainError("밸리데이션(검증 과정)중 발생하는 오류입니다.")
    ARGUMENT_NOT_VALID_ERROR(BAD_REQUEST, "GLOBAL_400_1", "잘못된 요청입니다."),

    @ExplainError("accessToken 만료시 발생하는 오류입니다.")
    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "토큰이 만료되었습니다."),

    @ExplainError("refreshToken 만료시 발생하는 오류입니다.")
    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증시간이 만료되었습니다. 인증토큰 재발급을 요청하세요"),
    @ExplainError("인증 토큰이 잘못됐을 때 발생하는 오류입니다.")
    INVALID_TOKEN(FORBIDDEN, "AUTH_403_2", "토큰의 형식이 일치하지 않습니다. 적절한 형식으로 다시 요청하세요"),
    @ExplainError("헤더에 올바른 accessToken을 담지않았을 때 발생하는 오류(형식 불일치 등)")
    ACCESS_TOKEN_NOT_EXIST(FORBIDDEN, "AUTH_403_3", "토큰이 존재하지 않습니다. 적절한 토큰을 헤더에 넣어주세요"),

    @ExplainError("없는 resource로 요청했습니다. 다른 요청으로 다시 시도해주세요")
    RESOURCE_NOT_FOUND(NOT_FOUND, "GLOBAL_404_1", "요청한 리소스를 서버에서 찾을 수 없습니다."),

    @ExplainError("요청한 메소드가 허용되지 않을 떄 발생하는 오류입니다.")
    SERVER_METHOD_NOT_ALLOWED(METHOD_NOT_ALLOWED, "GLOBAL_405_1", "요청한 메소드가 허용되지 않습니다."),

    @ExplainError("요청시간이 초과되었습니다.")
    RESOURCE_REQUEST_TIMEOUT(REQUEST_TIMEOUT, "GLOBAL_408_1", "요청 시간이 초과되었습니다."),

    @ExplainError("많은 요청을 보냈을 때 발생하는 오류입니다.")
    SERVER_TOO_MANY_REQUESTS(TOO_MANY_REQUESTS, "GLOBAL_429_1", "많은 요청을 보냈습니다. 잠시 후 다시 시도해주세요"),

    // 500 번대 에러
    @ExplainError("알 수 없는 오류 발생 서버 관리자에게 문의하세요.")
    INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "GLOBAL_500_1", "서버 오류, 관리자에게 문의하세요"),
    @ExplainError("SecurityContext를 찾을 수 없을 때 발생하는 오류입니다.")
    SECURITY_CONTEXT_NOT_FOUND(INTERNAL_SERVER, "GLOBAL_500_2", "SecurityContext를 찾을 수 없습니다"),

    @ExplainError("레디스 락을 사용할 수 없을 때 발생하는 오류입니다.")
    NOT_AVAILABLE_REDISSON_LOCK(INTERNAL_SERVER, "REDISSON_500_1", "락을 사용할 수 없습니다"),
    @ExplainError("잘못된 락 식별자를 사용했을 때 발생하는 오류입니다.")
    BAD_LOCK_IDENTIFIER(INTERNAL_SERVER, "REDISSON_500_2", "락의 키값이 잘못 세팅 되었습니다"),

    @ExplainError("서버가 현재 유지보수이거나 과부하로 이용할 수 없을 때 발생하는 오류입니다.")
    SERVER_UNAVAILABLE(
            SERVICE_UNAVAILABLE, "GLOBAL_503_1", "현재 유지보수 및 과부하로 서버를 이용할 수 없습니다. 잠시 후 다시 시도해주세요"),

    // 외부 서버
    @ExplainError("외부 서버에 잘못된 요청을 보냈을 때 발생하는 예외입니다.")
    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "FEIGN_400_1", "외부 서버에 잘못된 요청을 보냈습니다."),

    @ExplainError("외부 서버에 인증되지 않은 요청을 보냈을 때 발생하는 예외입니다.")
    OTHER_SERVER_UNAUTHORIZED(BAD_REQUEST, "FEIGN_400_2", "외부 서버에 인증되지 않은 요청을 보냈습니다."),

    @ExplainError("외부 서버에 접근이 거부된 요청을 보냈을 때 발생하는 예외입니다.")
    OTHER_SERVER_FORBIDDEN(BAD_REQUEST, "FEIGN_400_3", "외부 서버에 접근이 거부된 요청을 보냈습니다."),

    @ExplainError("외부 서버에 만료된 토큰으로 인증되지 않은 요청을 보냈습니다.")
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "FEIGN_400_4", "외부 서버에 만료된 토큰으로 인증되지 않은 요청을 보냈습니다."),

    @ExplainError("외부 서버에 잘못된 Resource를 요청했을 때 발생하는 예외입니다.")
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "FEIGN_400_5", "외부 서버에 요청한 리소스를 찾을 수 없습니다."),

    @ExplainError("외부 서버의 내부 서버 오류가 발생했을 때 발생하는 예외입니다.")
    OTHER_SERVER_INTERNAL_SERVER_ERROR(BAD_REQUEST, "FEIGN_400_6", "외부 서버의 알 수 없는 서버 오류가 발생했습니다."),
    ;

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().status(status).code(code).reason(reason).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getReason();
    }
}
