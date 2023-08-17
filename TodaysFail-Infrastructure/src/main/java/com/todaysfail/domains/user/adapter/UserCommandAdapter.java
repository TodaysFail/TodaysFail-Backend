package com.todaysfail.domains.user.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
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
            String profileImg,
            Boolean isDefaultImg,
            OauthProvider provider,
            String oid,
            UserStatus userStatus,
            UserRole userRole,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm) {
        UserEntity userEntity =
                UserEntity.registerUser(
                        name,
                        profileImg,
                        isDefaultImg,
                        provider,
                        oid,
                        userStatus,
                        userRole,
                        fcmToken,
                        pushAlarm,
                        eventAlarm);
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
