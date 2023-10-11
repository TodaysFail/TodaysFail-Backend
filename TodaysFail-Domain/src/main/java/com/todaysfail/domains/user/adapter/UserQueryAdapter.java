package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryPort {
    private final UserRepository userRepository;

    @Override
    public User findByOauthInfo(OauthInfo oauthInfo) {
        return userRepository
                .findByOauthInfo(oauthInfo)
                .orElseThrow(() -> UserNotFountException.EXCEPTION);
    }

    @Override
    public Boolean existsByOauthInfo(OauthInfo oauthInfo) {
        return userRepository.existsByOauthInfo(oauthInfo);
    }

    @Override
    public Boolean existsByName(String name) {
        return userRepository.existsByProfileName(name);
    }

    @Override
    public User queryUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> UserNotFountException.EXCEPTION);
    }
}
