package com.todaysfail.domains.auth.service;

import com.todaysfail.common.jwt.JwtTokenHelper;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.usecase.RefreshUseCase;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {
    private final RefreshTokenPort refreshTokenPort;
    private final JwtTokenHelper jwtTokenHelper;
    private final TokenGenerateUseCase tokenGenerateUseCase;
    private final UserQueryPort userQueryPort;

    @Override
    public TokenAndUser execute(String refreshToken) {
        refreshTokenPort.queryRefreshToken(refreshToken);
        Long refreshUserId = jwtTokenHelper.parseRefreshToken(refreshToken);
        User user = userQueryPort.queryUser(refreshUserId);
        user.refresh();
        return tokenGenerateUseCase.execute(user);
    }
}
