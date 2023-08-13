package com.todaysfail.domains.user.usecase;

import com.todaysfail.domains.user.domain.UserDetail;

public interface QueryUserUseCase {
    UserDetail queryMyInfo(Long userId);
}
