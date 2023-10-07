package com.todaysfail.domains.auth.service;

import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.auth.usecase.UserLoginUseCase;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserLoginUseCase {
    private final UserQueryPort userQueryPort;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final TokenGenerateUseCase tokenGenerateUseCase;

    @Override
    public TokenAndUser execute(String token, String fcmToken) {
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(token);
        User user = userQueryPort.findByOauthInfo(oauthInfo);
        user.login(fcmToken);
        return tokenGenerateUseCase.execute(user);
    }
}
