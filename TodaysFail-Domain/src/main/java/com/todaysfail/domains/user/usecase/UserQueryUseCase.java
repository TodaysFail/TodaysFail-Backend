package com.todaysfail.domains.user.usecase;

import com.todaysfail.common.vo.UserDetail;

public interface UserQueryUseCase {
    UserDetail queryUser(Long userId);
}
