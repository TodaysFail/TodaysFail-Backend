package com.todaysfail.domains.user.domain;

import com.todaysfail.common.type.user.OauthProvider;

public record UserDetail(
        Long userId,
        String profileImg,
        String name,
        Boolean isDefaultImg,
        OauthProvider oauthProvider,
        FcmNotification fcmInfo) {
    public static UserDetail from(User user) {
        return new UserDetail(
                user.getUserId(),
                user.getProfile().getProfileImg(),
                user.getProfile().getName(),
                user.getProfile().getIsDefaultImg(),
                user.getOauthInfo().getProvider(),
                user.getFcmNotification());
    }
}
