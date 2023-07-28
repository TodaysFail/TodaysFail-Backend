package com.todaysfail.domains.user.service;

import com.todaysfail.domains.user.domain.OauthInfoVo;
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
    public User execute(Profile profile, OauthInfoVo oauthInfoVo) {
        UserEntity userEntity =
                userCommandPort.registerUser(
                        profile.getName(), oauthInfoVo.getProvider(), oauthInfoVo.getOid());
        return User.registerUser(userEntity);
    }
}
