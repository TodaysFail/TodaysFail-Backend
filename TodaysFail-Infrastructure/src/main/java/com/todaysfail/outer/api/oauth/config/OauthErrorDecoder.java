package com.todaysfail.outer.api.oauth.config;

import com.todaysfail.common.dto.ErrorReason;
import com.todaysfail.common.exception.TodaysFailDynamicException;
import com.todaysfail.outer.api.oauth.dto.KakaoOauthErrorResponse;
import com.todaysfail.outer.api.oauth.exception.KakaoInvalidReqeustException;
import com.todaysfail.outer.api.oauth.exception.KakaoOauthErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;

public class OauthErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoOauthErrorResponse body = KakaoOauthErrorResponse.from(response);

        try {
            KakaoOauthErrorCode kakaoOauthErrorCode =
                    KakaoOauthErrorCode.valueOf(body.getErrorCode());
            ErrorReason errorReason = kakaoOauthErrorCode.getErrorReason();
            throw new TodaysFailDynamicException(
                    errorReason.getStatus(), errorReason.getCode(), errorReason.getReason());
        } catch (IllegalArgumentException e) {
            throw KakaoInvalidReqeustException.EXCEPTION;
        }
    }
}
