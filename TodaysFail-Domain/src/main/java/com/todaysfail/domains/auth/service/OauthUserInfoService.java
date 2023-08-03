package com.todaysfail.domains.auth.service;

import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.usecase.OauthUserInfoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OauthUserInfoService implements OauthUserInfoUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;

    @Override
    public OauthUserInfo execute(String accessToken) {
        return kakaoOauthHelper.getUserInfo(accessToken);
    }
}
