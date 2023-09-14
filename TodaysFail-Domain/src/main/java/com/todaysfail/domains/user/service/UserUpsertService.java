package com.todaysfail.domains.user.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.UserUpsertUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserUpsertService implements UserUpsertUseCase {
    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    @Override
    @Transactional
    @RedissonLock(lockName = "개발용회원가입", identifier = "oauthInfo")
    public User execute(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        return userQueryPort
                .findByOauthInfo(oauthInfo)
                .orElse(
                        userCommandPort.save(
                                User.builder()
                                        .profile(profile)
                                        .oauthInfo(oauthInfo)
                                        .fcmNotification(fcmNotification)
                                        .userRole(UserRole.USER)
                                        .userStatus(UserStatus.NORMAL)
                                        .build()));
    }
}
