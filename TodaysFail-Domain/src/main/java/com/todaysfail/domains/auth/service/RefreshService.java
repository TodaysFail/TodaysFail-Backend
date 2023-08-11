package com.todaysfail.domains.auth.service;

import org.springframework.stereotype.Service;

import com.todaysfail.common.jwt.JwtTokenHelper;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.usecase.RefreshUseCase;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.RefreshTokenPort;
import com.todaysfail.domains.user.port.UserQueryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshService implements RefreshUseCase {
	private final RefreshTokenPort refreshTokenPort;
	private final JwtTokenHelper jwtTokenHelper;
	private final TokenGenerateUseCase tokenGenerateUseCase;
	private final UserQueryPort userQueryPort;

	@Override
	public TokenAndUser execute(String refreshToken) {
		refreshTokenPort.queryRefreshToken(refreshToken);
		Long refreshUserId = jwtTokenHelper.parseRefreshToken(refreshToken);
		UserEntity userEntity = userQueryPort.queryUser(refreshUserId)
				.orElseThrow(() -> UserNotFountException.EXCEPTION);
		User user = User.from(userEntity);
		user.refresh();
		return tokenGenerateUseCase.execute(user);
	}
}
