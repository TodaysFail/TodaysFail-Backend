package com.todaysfail.api.web.auth.dto.response;

import com.todaysfail.domains.auth.domain.OauthUserInfo;

public record OauthUserInfoResponse(String profileImage, Boolean isDefaultImage, String name) {
    public static OauthUserInfoResponse from(OauthUserInfo oauthUserInfo) {
        return new OauthUserInfoResponse(
                oauthUserInfo.profileImage(), oauthUserInfo.isDefaultImage(), oauthUserInfo.name());
    }
}
