package com.todaysfail.domains.user.domain;

import com.todaysfail.aop.event.Events;
import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
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
    private AccountStatus accountStatus;
    private AccountRole accountRole;

    public static User of(
            Long userId,
            Profile profile,
            OauthInfo oauthInfo,
            AccountStatus accountStatus,
            AccountRole accountRole) {
        return new User(userId, profile, oauthInfo, accountStatus, accountRole);
    }

    public static User registerUser(UserEntity userEntity) {
        User user =
                new User(
                        userEntity.getId(),
                        Profile.from(userEntity.getName()),
                        OauthInfo.of(userEntity.getProvider(), userEntity.getOid()),
                        userEntity.getAccountStatus(),
                        userEntity.getAccountRole());
        Events.raise(new UserRegisterEvent(userEntity.getId()));
        return user;
    }
}
