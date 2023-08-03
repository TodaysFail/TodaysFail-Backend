package com.todaysfail.api.web.auth.mapper;

import com.todaysfail.api.web.auth.dto.response.AbleRegisterResponse;
import com.todaysfail.api.web.auth.dto.response.OauthLoginLinkResponse;
import com.todaysfail.api.web.auth.dto.response.OauthTokenResponse;
import com.todaysfail.api.web.auth.dto.response.OauthUserInfoResponse;
import com.todaysfail.api.web.auth.dto.response.TokenAndUserResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.auth.domain.OauthToken;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.domain.TokenAndUser;

@Mapper
public class AuthMapper {

    public TokenAndUserResponse toTokenAndUserResponse(TokenAndUser tokenAndUser) {
        return new TokenAndUserResponse(
                tokenAndUser.accessToken(),
                tokenAndUser.accessTokenExpireIn(),
                tokenAndUser.refreshToken(),
                tokenAndUser.refreshTokenExpireIn(),
                tokenAndUser.user());
    }

    public OauthLoginLinkResponse toOauthLoginLinkResponse(String kaKaoOauthLink) {
        return new OauthLoginLinkResponse(kaKaoOauthLink);
    }

    public OauthTokenResponse toOauthTokenResponse(OauthToken oauthToken) {
        return new OauthTokenResponse(
                oauthToken.accessToken(), oauthToken.refreshToken(), oauthToken.idToken());
    }

    public AbleRegisterResponse toAbleRegisterResponse(Boolean ableRegister) {
        return new AbleRegisterResponse(ableRegister);
    }

    public OauthUserInfoResponse toOauthUserInfoResponse(OauthUserInfo oauthUserInfo) {
        return new OauthUserInfoResponse(
                oauthUserInfo.profileImage(), oauthUserInfo.isDefaultImage(), oauthUserInfo.name());
    }
}
