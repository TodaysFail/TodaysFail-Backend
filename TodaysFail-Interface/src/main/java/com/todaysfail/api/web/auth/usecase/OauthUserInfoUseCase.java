package com.todaysfail.api.web.auth.usecase;

import com.todaysfail.api.web.auth.dto.response.OauthUserInfoResponse;
import com.todaysfail.api.web.auth.mapper.AuthMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class OauthUserInfoUseCase {
    private final AuthMapper authMapper;
    private final KakaoOauthHelper kakaoOauthHelper;

    public OauthUserInfoResponse execute(String accessToken) {
        OauthUserInfo oauthUserInfo = kakaoOauthHelper.getUserInfo(accessToken);
        return authMapper.toOauthUserInfoResponse(oauthUserInfo);
    }
}
