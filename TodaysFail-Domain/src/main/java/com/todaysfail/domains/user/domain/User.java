package com.todaysfail.domains.user.domain;

import com.todaysfail.aop.event.Events;
import com.todaysfail.common.BaseTimeEntity;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.exception.AlreadyDeletedUserException;
import com.todaysfail.domains.user.exception.UserForbiddenException;
import com.todaysfail.events.UserRegisterEvent;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Embedded private Profile profile;

    @Embedded private OauthInfo oauthInfo;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.NORMAL;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    @Embedded private FcmNotification fcmNotification;

    private LocalDateTime lastLoginAt = LocalDateTime.now();

    @PostPersist
    public void registerEvent() {
        Events.raise(new UserRegisterEvent(this.id));
    }

    public void login(String fcmToken) {
        if (userStatus != UserStatus.NORMAL) throw UserForbiddenException.EXCEPTION;
        this.lastLoginAt = LocalDateTime.now();
        updateToken(fcmToken);
    }

    private void updateToken(String fcmToken) {
        fcmNotification = fcmNotification.updateToken(fcmToken);
    }

    public void withDraw() {
        if (userStatus == UserStatus.DELETED) throw AlreadyDeletedUserException.EXCEPTION;
        userStatus = UserStatus.DELETED;
        profile = profile.withDraw();
        oauthInfo = oauthInfo.withDraw();
        fcmNotification = fcmNotification.disableAlarm();
    }

    public void refresh() {
        if (userStatus != UserStatus.NORMAL) throw UserForbiddenException.EXCEPTION;
        this.lastLoginAt = LocalDateTime.now();
    }
}
