package com.todaysfail.domains.user.service;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.common.utils.RandomUtil;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.domains.user.domain.AdjectiveType;
import com.todaysfail.domains.user.domain.AnimalType;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.Nickname;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.AlreadySignUpUserException;
import com.todaysfail.domains.user.exception.AlreadyUsedUserNameException;
import com.todaysfail.domains.user.exception.NicknameGenerationFailedException;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDomainService {
    private final UserQueryPort userQueryPort;
    private final UserCommandPort userCommandPort;

    public UserDetail queryUser(Long userId) {
        User user = userQueryPort.queryUser(userId);
        return UserDetail.from(user);
    }

    /**
     * try catch로 감싸더라도 queryPort에서 UserNotFoundException(RunTimeException) 발생하게되면 rollback-only로
     * 마킹되면서 외부메소드에서도 롤백이 일어나게되어 noRollbackFor를 사용
     * https://keencho.github.io/posts/transaction-rollback/
     */
    @Transactional(noRollbackFor = UserNotFountException.class)
    @RedissonLock(lockName = "개발용회원가입", identifier = "oauthInfo")
    public User upsert(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        try {
            return userQueryPort.findByOauthInfo(oauthInfo);
        } catch (UserNotFountException e) {
            return userCommandPort.save(
                    User.registerNormalUser(profile, oauthInfo, fcmNotification));
        }
    }

    @Transactional
    @RedissonLock(lockName = "유저등록", identifier = "oauthInfo")
    public User register(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification) {
        validUserCanRegister(oauthInfo, profile);
        return userCommandPort.save(User.registerNormalUser(profile, oauthInfo, fcmNotification));
    }

    public Boolean checkUserCanRegister(OauthInfo oauthInfo) {
        return userQueryPort.existsByOauthInfo(oauthInfo);
    }

    public Boolean checkUserName(Profile profile) {
        return userQueryPort.existsByName(profile.getName());
    }

    public void validUserCanRegister(OauthInfo oauthInfo, Profile profile) {
        if (checkUserCanRegister(oauthInfo)) throw AlreadySignUpUserException.EXCEPTION;
        if (checkUserName(profile)) throw AlreadyUsedUserNameException.EXCEPTION;
    }

    public Nickname randomNicknameGeneration() {
        for (int attempt = 1; attempt <= NICKNAME_GENERATE_MAX_ATTEMPTS; attempt++) {

            AdjectiveType randomAdjective = AdjectiveType.getRandomAdjective();
            AnimalType randomAnimal = AnimalType.getRandomAnimal();

            String generatedNickName =
                    randomAdjective.getValue()
                            + "_"
                            + randomAnimal.getValue()
                            + "_"
                            + RandomUtil.getRandomInt(1000);

            if (!userQueryPort.existsByName(generatedNickName)) {
                return Nickname.builder()
                        .nickname(generatedNickName)
                        .profileImageUrl(randomAnimal.getImageUrl())
                        .build();
            }
        }
        throw NicknameGenerationFailedException.EXCEPTION;
    }
}
