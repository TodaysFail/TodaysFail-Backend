package com.todaysfail.domains.auth.service;

import com.todaysfail.domains.auth.domain.OauthToken;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.helper.KakaoOauthHelper;
import com.todaysfail.domains.auth.usecase.RegisterUserUseCase;
import com.todaysfail.domains.auth.usecase.TokenGenerateUseCase;
import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.usecase.UserCanRegisterCheckUseCase;
import com.todaysfail.domains.user.usecase.UserRegisterUseCase;
import com.todaysfail.domains.user.usecase.UserUpsertUseCase;
import com.todaysfail.outer.api.oauth.dto.KakaoTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {
    private final KakaoOauthHelper kakaoOauthHelper;
    private final UserUpsertUseCase userUpsertUseCase;
    private final UserRegisterUseCase userRegisterUseCase;
    private final UserCanRegisterCheckUseCase userCanRegisterCheckUseCase;
    private final TokenGenerateUseCase tokenGenerateUseCase;

    @Override
    public TokenAndUser upsertKakaoOauthUser(String code) {
        String oauthAccessToken = kakaoOauthHelper.getOauthToken(code).getAccessToken();
        OauthUserInfo oauthUserInfo = kakaoOauthHelper.getUserInfo(oauthAccessToken);

        Profile profile =
                Profile.from(
                        oauthUserInfo.name(),
                        oauthUserInfo.profileImage(),
                        oauthUserInfo.isDefaultImage());
        OauthInfo oauthInfo = OauthInfo.of(oauthUserInfo.oauthId(), oauthUserInfo.oauthProvider());
        FcmNotification fcmNotification = FcmNotification.of("", false, false);

        User user = userUpsertUseCase.execute(profile, oauthInfo, fcmNotification);
        return tokenGenerateUseCase.execute(user);
    }

    @Override
    public String getKaKaoOauthLink() {
        return kakaoOauthHelper.getKakaoOauthLink();
    }

    @Override
    public OauthToken getCredentialFromKaKao(String code) {
        KakaoTokenResponse tokens = kakaoOauthHelper.getOauthToken(code);
        return new OauthToken(
                tokens.getAccessToken(), tokens.getRefreshToken(), tokens.getIdToken());
    }

    @Override
    public TokenAndUser registerUserByOCIDToken(
            String idToken,
            String profileImage,
            Boolean isDefaultImage,
            String name,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm) {

        Profile profile = Profile.from(name, profileImage, isDefaultImage);
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        FcmNotification fcmNotification = FcmNotification.of(fcmToken, pushAlarm, eventAlarm);
        User user = userRegisterUseCase.execute(profile, oauthInfo, fcmNotification);
        return tokenGenerateUseCase.execute(user);
    }

    @Override
    public Boolean checkAvailableRegister(String idToken) {
        OauthInfo oauthInfo = kakaoOauthHelper.getOauthInfoByIdToken(idToken);
        return userCanRegisterCheckUseCase.checkUserCanRegister(oauthInfo);
    }
}
