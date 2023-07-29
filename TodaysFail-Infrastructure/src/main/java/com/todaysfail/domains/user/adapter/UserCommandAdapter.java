package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class UserCommandAdapter implements UserCommandPort {
    private final UserRepository userRepository;

    @Override
    public UserEntity registerUser(
            String name,
            OauthProvider provider,
            String oid,
            AccountStatus accountStatus,
            AccountRole accountRole) {
        UserEntity userEntity =
                UserEntity.registerUser(name, provider, oid, accountStatus, accountRole);
        return userRepository.save(userEntity);
    }
}