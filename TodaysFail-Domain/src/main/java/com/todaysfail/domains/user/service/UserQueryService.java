package com.todaysfail.domains.user.service;

import com.todaysfail.domains.user.adapter.UserQueryAdapter;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.domain.UserDetail;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.usecase.UserQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {
    private final UserQueryAdapter userQueryAdapter;

    @Override
    public UserDetail queryMyInfo(Long userId) {
        UserEntity userEntity =
                userQueryAdapter
                        .queryUser(userId)
                        .orElseThrow(() -> UserNotFountException.EXCEPTION);
        return UserDetail.from(User.from(userEntity));
    }
}
