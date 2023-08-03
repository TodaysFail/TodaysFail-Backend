package com.todaysfail.domains.user.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.UserUpsertUseCase;
import java.util.Optional;
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
        User user = getUserByOauthInfo(oauthInfo);

        if (user == null) {
            UserEntity userEntity = createUserEntity(profile, oauthInfo, fcmNotification);
            user = User.registerUser(userEntity);
        }

        return user;
    }

    private User getUserByOauthInfo(OauthInfo oauthInfo) {
        Optional<UserEntity> userEntityOptional =
                userQueryPort.findByOauthInfo(oauthInfo.getOauthId(), oauthInfo.getProvider());
        return userEntityOptional.map(User::from).orElse(null);
    }

    private UserEntity createUserEntity(
            Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        return userCommandPort.registerUser(
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
    }
}
