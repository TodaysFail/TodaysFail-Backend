package com.todaysfail.domains.auth.usecase;

import com.todaysfail.domains.auth.domain.OauthToken;
import com.todaysfail.domains.auth.domain.TokenAndUser;

public interface RegisterUserUseCase {

    /** 백엔드 개발용 (회원가입, 로그인) Oauth accessToken을 통해 바로 회원가입, 로그인 처리 */
    TokenAndUser upsertKakaoOauthUser(String code);

    /** 백엔드 개발용으로 oauth 로그인할 링크를 발급 받습니다. */
    String getKaKaoOauthLink();

    /**
     * 백엔드 개발용으로 code 요청온것을 받아서 Oauth 토큰을 발급받습니다. accessToken, refreshToken, idToken을 발급받을 수 있습니다.
     */
    OauthToken getCredentialFromKaKao(String code);

    /** 어플리케이션에서 발급받은 idToken 을 통해 oauth 로그인 세션정보를 검증하고, registerRequest 의 유저정보로 유저회원가입 처리를 합니다. */
    TokenAndUser registerUserByOCIDToken(
            String idToken,
            String profileImage,
            Boolean isDefaultImage,
            String name,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm);

    /** 어플리케이션에서 로그인 or 회원가입시 이미 등록한 유저인지 판단할 수 없으므로, 한번 회원가입한 유저인지 확인하는 작업을 거칩니다. */
    Boolean checkAvailableRegister(String idToken);
}
