package com.todaysfail.domains.auth.usecase;

import com.todaysfail.domains.auth.domain.TokenAndUser;

public interface UserLoginUseCase {
	TokenAndUser execute(String token, String fcmToken);
}
