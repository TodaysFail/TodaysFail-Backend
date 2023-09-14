package com.todaysfail.api.web.auth.dto.response;

import com.todaysfail.common.vo.UserDetail;

public record TokenAndUserResponse(
        String accessToken,
        Long accessTokenExpireIn,
        String refreshToken,
        Long refreshTokenExpireIn,
        UserDetail user) {}
