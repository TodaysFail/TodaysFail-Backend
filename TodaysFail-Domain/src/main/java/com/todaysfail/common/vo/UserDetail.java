package com.todaysfail.common.vo;

import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.User;

public record UserDetail(
        Long userId,
        String profileImg,
        String name,
        Boolean isDefaultImg,
        OauthProvider oauthProvider,
        FcmNotification fcmInfo) {
    public static UserDetail from(User user) {
        return new UserDetail(
                user.getId(),
                user.getProfile().getProfileImg(),
                user.getProfile().getName(),
                user.getProfile().getIsDefaultImg(),
                user.getOauthInfo().getProvider(),
                user.getFcmNotification());
    }
}
