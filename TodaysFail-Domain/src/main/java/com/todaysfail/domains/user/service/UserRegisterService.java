package com.todaysfail.domains.user.service;

import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.usecase.UserRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRegisterService implements UserRegisterUseCase {
    private final UserCommandPort userCommandPort;

    @Override
    @Transactional
    public User execute(Profile profile, OauthInfo oauthInfo) {
        UserEntity userEntity =
                userCommandPort.registerUser(
                        profile.getName(),
                        oauthInfo.getProvider(),
                        oauthInfo.getOid(),
                        AccountStatus.NORMAL,
                        AccountRole.USER);
        return User.registerUser(userEntity);
    }
}