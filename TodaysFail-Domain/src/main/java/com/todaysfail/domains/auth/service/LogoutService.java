package com.todaysfail.domains.auth.service;

import org.springframework.stereotype.Service;

import com.todaysfail.domains.auth.usecase.LogoutUseCase;
import com.todaysfail.domains.user.port.RefreshTokenPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {
	private final RefreshTokenPort refreshTokenPort;

	@Override
	public void execute() {
		// TODO: Security 구현 후 context 정보 조회 후 로그아웃
	}
}
