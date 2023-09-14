package com.todaysfail.domains.user.service;

import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserQueryPort;
import com.todaysfail.domains.user.usecase.UserQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService implements UserQueryUseCase {
    private final UserQueryPort userQueryPort;

    @Override
    public UserDetail queryUser(Long userId) {
        User user =
                userQueryPort.queryUser(userId).orElseThrow(() -> UserNotFountException.EXCEPTION);
        return UserDetail.from(user);
    }
}
