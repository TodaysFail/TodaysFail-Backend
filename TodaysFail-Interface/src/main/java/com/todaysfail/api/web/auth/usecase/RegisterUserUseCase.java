package com.todaysfail.api.web.auth.usecase;

import com.todaysfail.api.web.auth.dto.request.UserRegisterRequest;
import com.todaysfail.api.web.auth.dto.response.AbleRegisterResponse;
import com.todaysfail.api.web.auth.dto.response.OauthLoginLinkResponse;
import com.todaysfail.api.web.auth.dto.response.OauthTokenResponse;
import com.todaysfail.api.web.auth.dto.response.TokenAndUserResponse;
import com.todaysfail.api.web.auth.mapper.AuthMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.auth.domain.OauthToken;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.helper.TokenGenerateHelper;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.service.UserDomainService;
import com.todaysfail.outer.api.oauth.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterUserUseCase {
    private final AuthMapper authMapper;
    private final KakaoOauthHelper kakaoOauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;
    private final UserDomainService userDomainService;

    /** 백엔드 개발용 (회원가입, 로그인) Oauth accessToken을 통해 바로 회원가입, 로그인 처리 */
    public TokenAndUserResponse upsertKakaoOauthUser(String code) {
        String oauthAccessToken = kakaoOauthHelper.getOauthToken(code).getAccessToken();
        OauthUserInfo oauthUserInfo = kakaoOauthHelper.getUserInfo(oauthAccessToken);

        Profile profile =
                Profile.builder()
                        .name(oauthUserInfo.name())
                        .profileImg(oauthUserInfo.profileImage())
                        .isDefaultImg(oauthUserInfo.isDefaultImage())
                        .build();
        OauthInfo oauthInfo =
                OauthInfo.builder()
                        .oauthId(oauthUserInfo.oauthId())
                        .provider(oauthUserInfo.oauthProvider())
                        .build();
        FcmNotification fcmNotification =
                FcmNotification.builder().fcmToken("").pushAlarm(false).eventAlarm(false).build();

        User user = userDomainService.upsert(profile, oauthInfo, fcmNotification);
        TokenAndUser tokenAndUser = tokenGenerateHelper.tokenGenerate(user);
        return authMapper.toTokenAndUserResponse(tokenAndUser);
    }

    /** 백엔드 개발용으로 oauth 로그인할 링크를 발급 받습니다. */
    public OauthLoginLinkResponse getKaKaoOauthLink() {
        String kakaoOauthLink = kakaoOauthHelper.getKakaoOauthLink();
        return authMapper.toOauthLoginLinkResponse(kakaoOauthLink);
    }

    /**
     * 백엔드 개발용으로 code 요청온것을 받아서 Oauth 토큰을 발급받습니다. accessToken, refreshToken, idToken을 발급받을 수 있습니다.
     */
    public OauthTokenResponse getCredentialFromKaKao(String code) {
        KakaoTokenResponse tokens = kakaoOauthHelper.getOauthToken(code);
        OauthToken oauthToken =
                new OauthToken(
                        tokens.getAccessToken(), tokens.getRefreshToken(), tokens.getIdToken());
        return authMapper.toOauthTokenResponse(oauthToken);
    }

    /** 어플리케이션에서 발급받은 idToken 을 통해 oauth 로그인 세션정보를 검증하고, registerRequest 의 유저정보로 유저회원가입 처리를 합니다. */
    public TokenAndUserResponse registerUserByOCIDToken(
            String idToken, UserRegisterRequest userRegisterRequest) {
        Profile profile =
                Profile.builder()
                        .name(userRegisterRequest.name())
                        .profileImg(userRegisterRequest.profileImage())
                        .isDefaultImg(userRegisterRequest.isDefaultImage())
                        .build();
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        FcmNotification fcmNotification =
                FcmNotification.builder()
                        .fcmToken(userRegisterRequest.fcmToken())
                        .pushAlarm(userRegisterRequest.pushAlarm())
                        .eventAlarm(userRegisterRequest.eventAlarm())
                        .build();
        User user = userDomainService.register(profile, oauthInfo, fcmNotification);
        TokenAndUser tokenAndUser = tokenGenerateHelper.tokenGenerate(user);
        return authMapper.toTokenAndUserResponse(tokenAndUser);
    }

    /** 어플리케이션에서 로그인 or 회원가입시 이미 등록한 유저인지 판단할 수 없으므로, 한번 회원가입한 유저인지 확인하는 작업을 거칩니다. */
    public AbleRegisterResponse checkAvailableRegister(String idToken) {
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        Boolean ableRegister = userDomainService.checkUserCanRegister(oauthInfo);
        return authMapper.toAbleRegisterResponse(ableRegister);
    }
}
