package com.todaysfail.api.web.auth.dto.response;

import com.todaysfail.domains.user.domain.UserDetail;

public record TokenAndUserResponse(
        String accessToken,
        Long accessTokenExpireIn,
        String refreshToken,
        Long refreshTokenExpireIn,
        UserDetail user) {}
