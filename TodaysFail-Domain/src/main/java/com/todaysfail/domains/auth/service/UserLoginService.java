package com.todaysfail.domains.auth.service;

import org.springframework.stereotype.Service;

import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.auth.usecase.UserLoginUseCase;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.entity.UserEntity;
import com.todaysfail.domains.user.exception.UserNotFountException;
import com.todaysfail.domains.user.port.UserCommandPort;
import com.todaysfail.domains.user.port.UserQueryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserLoginService implements UserLoginUseCase {
	private final UserQueryPort userQueryPort;
	private final UserCommandPort userCommandPort;
	private final KakaoOauthHelper kakaoOauthHelper;
	private final TokenGenerateUseCase tokenGenerateUseCase;

	@Override
	public TokenAndUser execute(String token, String fcmToken) {
		OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(token);
		UserEntity userEntity = userQueryPort.findByOauthInfo(oauthInfo.getOauthId(), oauthInfo.getProvider())
				.orElseThrow(() -> UserNotFountException.EXCEPTION);
		User user = User.from(userEntity);
		user.login(fcmToken);
		userCommandPort.save(user.toEntity());
		return tokenGenerateUseCase.execute(user);
	}
}
