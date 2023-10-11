package com.todaysfail.api.web.auth.usecase;

import com.todaysfail.api.web.auth.dto.response.TokenAndUserResponse;
import com.todaysfail.api.web.auth.mapper.AuthMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.helper.TokenGenerateHelper;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserCommandPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserLoginUseCase {
    private final AuthMapper authMapper;
    private final UserCommandPort userCommandPort;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;

    public TokenAndUserResponse execute(String token, String fcmToken) {
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(token);
        User user = userCommandPort.queryUser(oauthInfo);
        user.login(fcmToken);
        TokenAndUser tokenAndUser = tokenGenerateHelper.tokenGenerate(user);
        return authMapper.toTokenAndUserResponse(tokenAndUser);
    }
}
