package com.todaysfail.api.web.user.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.port.UserCommandPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserWithDrawUseCase {
    private final RefreshTokenPort refreshTokenPort;
    private final UserCommandPort userCommandPort;
    private final KakaoOauthHelper kakaoOauthHelper;

    public void execute() {
        final Long userId = SecurityUtils.getCurrentUserId();
        refreshTokenPort.deleteByUserId(userId);
        User user = userCommandPort.queryUser(userId);
        String oauthId = user.getOauthInfo().getOauthId();
        user.withDraw();
        kakaoOauthHelper.unlink(oauthId);
    }
}
