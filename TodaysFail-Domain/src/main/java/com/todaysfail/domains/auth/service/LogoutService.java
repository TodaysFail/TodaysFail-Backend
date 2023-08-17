package com.todaysfail.domains.auth.service;

import com.todaysfail.domains.auth.usecase.LogoutUseCase;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public void execute(Long userId) {
        refreshTokenPort.deleteByUserId(userId);
    }
}
