package com.todaysfail.domains.user.service;

import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.UserCanRegisterCheckUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCanRegisterCheckService implements UserCanRegisterCheckUseCase {
    private final UserQueryPort userQueryPort;

    @Override
    public Boolean checkUserCanRegister(OauthInfo oauthInfo) {
        return userQueryPort.existsByOauthInfo(oauthInfo.getOauthId(), oauthInfo.getProvider());
    }
}
