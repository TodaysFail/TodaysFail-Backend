package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.common.exception.RefreshTokenExpiredException;
import com.todaysfail.domains.user.domain.RefreshTokenRedisEntity;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class RefreshTokenAdapter implements RefreshTokenPort {
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshTokenRedisEntity queryRefreshToken(String refreshToken) {
        return refreshTokenRepository
                .findByRefreshToken(refreshToken)
                .orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);
    }

    @Override
    public RefreshTokenRedisEntity save(RefreshTokenRedisEntity refreshToken) {
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteById(userId.toString());
    }
}
