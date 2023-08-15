package com.todaysfail.outer.api.oauth.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.todaysfail.common.exception.GlobalErrorCode;
import com.todaysfail.common.exception.TodaysFailDynamicException;
import feign.Response;
import java.io.IOException;
import java.io.InputStream;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoOauthErrorResponse {
    private String error;
    private String errorCode;
    private String errorDescription;

    public static KakaoOauthErrorResponse from(Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(bodyIs, KakaoOauthErrorResponse.class);
        } catch (IOException e) {
            GlobalErrorCode internalServerError = GlobalErrorCode.INTERNAL_SERVER_ERROR;
            throw new TodaysFailDynamicException(
                    internalServerError.getStatus(),
                    internalServerError.getCode(),
                    internalServerError.getReason());
        }
    }
}
