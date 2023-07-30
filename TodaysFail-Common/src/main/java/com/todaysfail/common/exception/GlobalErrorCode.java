package com.todaysfail.common.exception;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.dto.ErrorReason;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode implements BaseErrorCode {
    INTERNAL_SERVER_ERROR(INTERNAL_SERVER, "GLOBAL_500_1", "서버 오류, 관리자에게 문의하세요"),

    ARGUMENT_NOT_VALID_ERROR(BAD_REQUEST, "GLOBAL_400_1", "잘못된 요청입니다."),

    TOKEN_EXPIRED(UNAUTHORIZED, "AUTH_401_1", "토큰이 만료되었습니다."),

    REFRESH_TOKEN_EXPIRED(FORBIDDEN, "AUTH_403_1", "인증시간이 만료되었습니다. 인증토큰 재발급을 요청하세요"),

    INVALID_TOKEN(FORBIDDEN, "AUTH_403_2", "토큰의 형식이 일치하지 않습니다. 적절한 형식으로 다시 요청하세요"),

    ACCESS_TOKEN_NOT_EXIST(UNAUTHORIZED, "AUTH_401_2", "토큰이 존재하지 않습니다. 적절한 토큰을 헤더에 넣어주세요"),

    ACCESS_TOKEN_NOT_FOUND(FORBIDDEN, "AUTH_403_3", "토큰이 존재하지 않습니다. 적절한 토큰을 헤더에 넣어주세요"),

    RESOURCE_NOT_FOUND(
            NOT_FOUND, "GLOBAL_404_1", "The requested resource was not found on the server."),

    RESOURCE_REQUEST_TIMEOUT(REQUEST_TIMEOUT, "GLOBAL_408_1", "요청 시간이 초과되었습니다."),

    SERVER_TOO_MANY_REQUESTS(TOO_MANY_REQUESTS, "GLOBAL_429_1", "많은 요청을 보냈습니다. 잠시 후 다시 시도해주세요"),

    SERVER_METHOD_NOT_ALLOWED(METHOD_NOT_ALLOWED, "GLOBAL_405_1", "요청한 메소드가 허용되지 않습니다."),

    SERVER_UNAVAILABLE(
            SERVICE_UNAVAILABLE, "GLOBAL_503_1", "현재 유지보수 및 과부하로 서버를 이용할 수 없습니다. 잠시 후 다시 시도해주세요"),

    OTHER_SERVER_BAD_REQUEST(BAD_REQUEST, "FEIGN_400_1", "Other server bad request"),
    OTHER_SERVER_UNAUTHORIZED(BAD_REQUEST, "FEIGN_400_2", "Other server unauthorized"),
    OTHER_SERVER_FORBIDDEN(BAD_REQUEST, "FEIGN_400_3", "Other server forbidden"),
    OTHER_SERVER_EXPIRED_TOKEN(BAD_REQUEST, "FEIGN_400_4", "Other server expired token"),
    OTHER_SERVER_NOT_FOUND(BAD_REQUEST, "FEIGN_400_5", "Other server not found error"),
    OTHER_SERVER_INTERNAL_SERVER_ERROR(
            BAD_REQUEST, "FEIGN_400_6", "Other server internal server error"),

    NOT_AVAILABLE_REDISSON_LOCK(500, "REDISSON_500_1", "락을 사용할 수 없습니다"),
    BAD_LOCK_IDENTIFIER(500, "REDISSON_500_2", "락의 키값이 잘못 세팅 되었습니다"),
    ;

    private Integer status;
    private String code;
    private String reason;

    @Override
    public ErrorReason getErrorReason() {
        return ErrorReason.builder().status(status).code(code).reason(reason).build();
    }
}
