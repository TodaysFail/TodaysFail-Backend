package com.todaysfail.domains.user.service;

import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.usecase.UserWithDrawUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserWithDrawService implements UserWithDrawUseCase {
    private final UserCommandPort userCommandPort;
    private final RefreshTokenPort refreshTokenPort;
    private final KakaoOauthHelper kakaoOauthHelper;

    @Override
    @Transactional
    public void execute(Long userId) {
        refreshTokenPort.deleteByUserId(userId);
        User user =
                userCommandPort
                        .queryUser(userId)
                        .orElseThrow(() -> UserNotFountException.EXCEPTION);
        String oauthId = user.getOauthInfo().getOauthId();
        user.withDraw();
        kakaoOauthHelper.unlink(oauthId);
    }
}
