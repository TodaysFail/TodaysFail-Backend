package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserQueryAdapter implements UserQueryPort {
    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findByOauthInfo(String oauthId, OauthProvider provider) {
        return userRepository.findByOidAndProvider(oauthId, provider);
    }

    @Override
    public Boolean existsByOauthInfo(String oauthId, OauthProvider provider) {
        return userRepository.existsByOidAndProvider(oauthId, provider);
    }

    @Override
    public Boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    @Override
    public Optional<UserEntity> queryUser(Long userId) {
        return userRepository.findById(userId);
    }
}
