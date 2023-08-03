package com.todaysfail.domains.user.entity;

import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.BaseTimeEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String profileImg;

    private Boolean isDefaultImg;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    private String oid;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.NORMAL;

    @Enumerated(EnumType.STRING)
    private UserRole userRole = UserRole.USER;

    private String fcmToken = "";

    private Boolean pushAlarm;

    private Boolean eventAlarm;

    private LocalDateTime lastLoginAt = LocalDateTime.now();

    private UserEntity(
            String name,
            String profileImg,
            Boolean isDefaultImg,
            OauthProvider provider,
            String oid,
            UserStatus userStatus,
            UserRole userRole,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm) {
        this.name = name;
        this.profileImg = profileImg;
        this.isDefaultImg = isDefaultImg;
        this.provider = provider;
        this.oid = oid;
        this.userStatus = userStatus;
        this.userRole = userRole;
        this.fcmToken = fcmToken;
        this.pushAlarm = pushAlarm;
        this.eventAlarm = eventAlarm;
    }

    public static UserEntity registerUser(
            String name,
            String profileImg,
            Boolean isDefaultImg,
            OauthProvider provider,
            String oid,
            UserStatus userStatus,
            UserRole userRole,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm) {
        return new UserEntity(
                name,
                profileImg,
                isDefaultImg,
                provider,
                oid,
                userStatus,
                userRole,
                fcmToken,
                pushAlarm,
                eventAlarm);
    }
}
