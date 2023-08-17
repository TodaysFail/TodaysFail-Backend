package com.todaysfail.domains.user.domain;

public record UserInfo(Long userId, String profileImg, String name, Boolean isDefaultImg) {
    public static UserInfo from(User user) {
        return new UserInfo(
                user.getUserId(),
                user.getProfile().getProfileImg(),
                user.getProfile().getName(),
                user.getProfile().getIsDefaultImg());
    }
}
