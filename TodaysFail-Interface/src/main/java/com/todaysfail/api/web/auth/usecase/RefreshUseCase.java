package com.todaysfail.api.web.auth.usecase;

import com.todaysfail.api.web.auth.dto.response.TokenAndUserResponse;
import com.todaysfail.api.web.auth.mapper.AuthMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.common.jwt.JwtTokenHelper;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.TokenGenerateHelper;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RefreshUseCase {
    private final AuthMapper authMapper;
    private final RefreshTokenPort refreshTokenPort;
    private final JwtTokenHelper jwtTokenHelper;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final UserQueryPort userQueryPort;

    public TokenAndUserResponse execute(String refreshToken) {
        refreshTokenPort.queryRefreshToken(refreshToken);
        Long refreshUserId = jwtTokenHelper.parseRefreshToken(refreshToken);
        User user = userQueryPort.queryUser(refreshUserId);
        user.refresh();
        TokenAndUser tokenAndUser = tokenGenerateHelper.tokenGenerate(user);
        return authMapper.toTokenAndUserResponse(tokenAndUser);
    }
}
