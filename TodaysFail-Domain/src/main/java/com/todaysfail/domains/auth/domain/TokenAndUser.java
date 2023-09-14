package com.todaysfail.domains.auth.domain;

import com.todaysfail.common.vo.UserDetail;

public record TokenAndUser(
        String accessToken,
        Long accessTokenExpireIn,
        String refreshToken,
        Long refreshTokenExpireIn,
        UserDetail user) {}
