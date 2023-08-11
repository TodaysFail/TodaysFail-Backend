package com.todaysfail.domains.auth.usecase;

import com.todaysfail.domains.auth.domain.TokenAndUser;

public interface RefreshUseCase {
    TokenAndUser execute(String refreshToken);
}
