package com.todaysfail.domains.user.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
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
    @Transactional(noRollbackFor = UserNotFountException.class)
    /**
     * try catch로 감싸더라도 queryPort에서 UserNotFoundException(RunTimeException) 발생하게되면 rollback-only로
     * 마킹되면서 외부메소드에서도 롤백이 일어나게되어 noRollbackFor를 사용
     * https://keencho.github.io/posts/transaction-rollback/
     */
    @RedissonLock(lockName = "개발용회원가입", identifier = "oauthInfo")
    public User execute(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        try {
            return userQueryPort.findByOauthInfo(oauthInfo);
        } catch (UserNotFountException e) {
            return userCommandPort.save(
                    User.builder()
                            .profile(profile)
                            .oauthInfo(oauthInfo)
                            .fcmNotification(fcmNotification)
                            .userRole(UserRole.USER)
                            .userStatus(UserStatus.NORMAL)
                            .build());
        }
    }
}
