package com.todaysfail.domains.auth.domain;

import com.todaysfail.common.type.user.OauthProvider;

public record OauthUserInfo(
        String oauthId,
        String profileImage,
        Boolean isDefaultImage,
        String name,
        OauthProvider oauthProvider) {
    public static OauthUserInfo of(
            String oauthId,
            String profileImage,
            Boolean isDefaultImage,
            String name,
            OauthProvider oauthProvider) {
        return new OauthUserInfo(oauthId, profileImage, isDefaultImage, name, oauthProvider);
    }
}
