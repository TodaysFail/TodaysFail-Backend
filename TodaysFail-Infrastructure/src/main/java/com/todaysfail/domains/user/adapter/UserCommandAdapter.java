package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.user.UserEntity;
import com.todaysfail.domains.user.domain.OauthInfoVo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
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
    public User registerUser(Profile profile, OauthInfoVo oauthInfoVo) {
        UserEntity userEntity =
                UserEntity.builder()
                        .name(profile.name())
                        .provider(oauthInfoVo.provider())
                        .oid(oauthInfoVo.oid())
                        .build();
        return userRepository.save(userEntity).toDomain();
    }
}
