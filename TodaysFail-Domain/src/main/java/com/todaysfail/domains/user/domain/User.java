package com.todaysfail.domains.user.domain;

import java.time.LocalDateTime;

import com.todaysfail.aop.event.Events;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.exception.UserForbiddenException;
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
    private LocalDateTime lastLoginAt;


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
                        userEntity.getEventAlarm()),
                userEntity.getLastLoginAt());
    }

    public static User of(
            Long userId,
            Profile profile,
            OauthInfo oauthInfo,
            UserStatus userStatus,
            UserRole userRole,
            FcmNotification fcmNotification,
            LocalDateTime lastLoginAt) {
        return new User(userId, profile, oauthInfo, userStatus, userRole, fcmNotification, lastLoginAt);
    }

    public UserEntity toEntity() {
        return UserEntity.from(
                this.getUserId(),
                this.getProfile().getName(),
                this.getProfile().getProfileImg(),
                this.getProfile().getIsDefaultImg(),
                this.getOauthInfo().getProvider(),
                this.getOauthInfo().getOauthId(),
                this.getUserStatus(),
                this.getUserRole(),
                this.getFcmNotification().getFcmToken(),
                this.getFcmNotification().getPushAlarm(),
                this.getFcmNotification().getEventAlarm(),
                this.getLastLoginAt());
    }

    public static User registerUser(UserEntity userEntity) {
        User user = User.from(userEntity);
        Events.raise(new UserRegisterEvent(userEntity.getId()));
        return user;
    }

    public void login(String fcmToken) {
        if (userStatus != UserStatus.NORMAL) {
            throw UserForbiddenException.EXCEPTION;
        }
        lastLoginAt = LocalDateTime.now();
        updateToken(fcmToken);
    }

    private void updateToken(String fcmToken) {
        fcmNotification = FcmNotification.updateToken(this.fcmNotification, fcmToken);
    }

    public void refresh() {
        if (userStatus != UserStatus.NORMAL) {
            throw UserForbiddenException.EXCEPTION;
        }
        lastLoginAt = LocalDateTime.now();
    }
}
