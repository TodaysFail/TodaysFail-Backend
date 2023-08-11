package com.todaysfail.domains.auth.usecase;

import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.user.domain.User;

public interface TokenGenerateUseCase {
    TokenAndUser execute(User user);
}
