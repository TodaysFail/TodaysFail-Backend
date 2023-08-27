package com.todaysfail.domains.user.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.exception.AlreadySignUpUserException;
import com.todaysfail.domains.user.exception.AlreadyUsedUserNameException;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserCommandPort userCommandPort;
    private final UserQueryPort userQueryPort;

    @Override
    @Transactional
    @RedissonLock(lockName = "유저등록", identifier = "oauthInfo")
    public User execute(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        validUserCanRegister(oauthInfo, profile);
        UserEntity userEntity =
                userCommandPort.registerUser(
                        profile.getName(),
                        profile.getProfileImg(),
                        profile.getIsDefaultImg(),
                        oauthInfo.getProvider(),
                        oauthInfo.getOauthId(),
                        UserStatus.NORMAL,
                        UserRole.USER,
                        fcmNotification.getFcmToken(),
                        fcmNotification.getPushAlarm(),
                        fcmNotification.getEventAlarm());
        return User.registerUser(userEntity);
    }

    public Boolean checkUserCanRegister(OauthInfo oauthInfo) {
        Boolean aBoolean =
                userQueryPort.existsByOauthInfo(oauthInfo.getOauthId(), oauthInfo.getProvider());
        return aBoolean;
    }

    public Boolean checkUserName(Profile profile) {
        return userQueryPort.existsByName(profile.getName());
    }

    public void validUserCanRegister(OauthInfo oauthInfo, Profile profile) {
        if (checkUserCanRegister(oauthInfo)) throw AlreadySignUpUserException.EXCEPTION;
        if (checkUserName(profile)) throw AlreadyUsedUserNameException.EXCEPTION;
    }
}
