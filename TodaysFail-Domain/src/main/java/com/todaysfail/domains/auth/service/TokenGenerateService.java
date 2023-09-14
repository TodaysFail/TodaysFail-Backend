package com.todaysfail.domains.auth.service;

import com.todaysfail.common.jwt.JwtTokenHelper;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.user.domain.RefreshTokenRedisEntity;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenGenerateService implements TokenGenerateUseCase {
    private final JwtTokenHelper jwtTokenHelper;
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public TokenAndUser execute(User user) {
        String newAccessToken =
                jwtTokenHelper.generateAccessToken(user.getId(), user.getUserRole().getValue());
        String newRefreshToken = jwtTokenHelper.generateRefreshToken(user.getId());
        RefreshTokenRedisEntity refreshTokenRedisEntity =
                RefreshTokenRedisEntity.builder()
                        .id(user.getId())
                        .refreshToken(newRefreshToken)
                        .ttl(jwtTokenHelper.getRefreshTokenTTlSecond())
                        .build();
        refreshTokenPort.save(refreshTokenRedisEntity);
        UserDetail userDetail = UserDetail.from(user);
        return new TokenAndUser(
                newAccessToken,
                jwtTokenHelper.getAccessTokenTTlSecond(),
                newRefreshToken,
                jwtTokenHelper.getRefreshTokenTTlSecond(),
                userDetail);
    }
}
