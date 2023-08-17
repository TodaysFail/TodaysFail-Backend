package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.entity.RefreshTokenRedisEntity;

public interface RefreshTokenPort {
    RefreshTokenRedisEntity queryRefreshToken(String refreshToken);

    RefreshTokenRedisEntity save(RefreshTokenRedisEntity refreshToken);

    void deleteByUserId(Long userId);
}
