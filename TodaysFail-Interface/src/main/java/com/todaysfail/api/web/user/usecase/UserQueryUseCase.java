package com.todaysfail.api.web.user.usecase;

import com.todaysfail.api.web.user.mapper.UserMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserQueryPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserQueryUseCase {
    private final UserMapper userMapper;
    private final UserQueryPort userQueryPort;

    public UserDetail queryMyInfo() {
        final Long userId = SecurityUtils.getCurrentUserId();
        User user = userQueryPort.queryUser(userId);
        return userMapper.toUserDetail(user);
    }
}
