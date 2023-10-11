package com.todaysfail.api.web.auth.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LogoutUseCase {
    private final RefreshTokenPort refreshTokenPort;

    public void execute() {
        Long userId = SecurityUtils.getCurrentUserId();
        refreshTokenPort.deleteByUserId(userId);
    }
}
