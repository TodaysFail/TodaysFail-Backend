package com.todaysfail.domains.user.domain;

import com.todaysfail.aop.event.Events;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.events.UserRegisterEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long userId;
    private Profile profile;
    private OauthInfo oauthInfo;
    private UserStatus userStatus;
    private UserRole userRole;
    private FcmNotification fcmNotification;

    public static User of(
            Long userId,
            Profile profile,
            OauthInfo oauthInfo,
            UserStatus userStatus,
            UserRole userRole,
            FcmNotification fcmNotification) {
        return new User(userId, profile, oauthInfo, userStatus, userRole, fcmNotification);
    }

    public static User from(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                Profile.from(
                        userEntity.getName(),
                        userEntity.getProfileImg(),
                        userEntity.getIsDefaultImg()),
                OauthInfo.of(userEntity.getOid(), userEntity.getProvider()),
                userEntity.getUserStatus(),
                userEntity.getUserRole(),
                FcmNotification.of(
                        userEntity.getFcmToken(),
                        userEntity.getPushAlarm(),
                        userEntity.getEventAlarm()));
    }

    public static User registerUser(UserEntity userEntity) {
        User user =
                new User(
                        userEntity.getId(),
                        Profile.from(
                                userEntity.getName(),
                                userEntity.getProfileImg(),
                                userEntity.getIsDefaultImg()),
                        OauthInfo.of(userEntity.getOid(), userEntity.getProvider()),
                        userEntity.getUserStatus(),
                        userEntity.getUserRole(),
                        FcmNotification.of(
                                userEntity.getFcmToken(),
                                userEntity.getPushAlarm(),
                                userEntity.getEventAlarm()));
        Events.raise(new UserRegisterEvent(userEntity.getId()));
        return user;
    }
}
