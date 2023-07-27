package com.todaysfail.domains.user.domain;

import com.todaysfail.aop.event.Events;
import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.events.UserRegisterEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private Long userId;
    private Profile profile;
    private OauthInfoVo oauthInfoVo;
    private AccountStatus accountStatus;
    private AccountRole accountRole;

    public static User of(
            Long userId,
            Profile profile,
            OauthInfoVo oauthInfoVo,
            AccountStatus accountStatus,
            AccountRole accountRole) {
        return new User(userId, profile, oauthInfoVo, accountStatus, accountRole);
    }

    public void userRegisterEventRaise() {
        UserRegisterEvent userRegisterEvent = UserRegisterEvent.builder().userId(userId).build();
        Events.raise(userRegisterEvent);
    }
}
