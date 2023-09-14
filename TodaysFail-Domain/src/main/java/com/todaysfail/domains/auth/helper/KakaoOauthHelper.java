package com.todaysfail.domains.auth.helper;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.annotation.Helper;
import com.todaysfail.common.dto.OIDCDecodePayload;
import com.todaysfail.common.properties.OauthProperties;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.outer.api.oauth.client.KakaoInfoClient;
import com.todaysfail.outer.api.oauth.client.KakaoOauthClient;
import com.todaysfail.outer.api.oauth.dto.KakaoInformationResponse;
import com.todaysfail.outer.api.oauth.dto.KakaoTokenResponse;
import com.todaysfail.outer.api.oauth.dto.OIDCPublicKeysResponse;
import com.todaysfail.outer.api.oauth.dto.UnlinkKaKaoTarget;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class KakaoOauthHelper {
    private final OauthProperties oauthProperties;
    private final KakaoInfoClient kakaoInfoClient;
    private final KakaoOauthClient kakaoOauthClient;
    private final OauthOIDCHelper oauthOIDCHelper;

    public String getKakaoOauthLink() {
        return oauthProperties.getKakaoBaseUrl()
                + "/oauth/authorize?client_id="
                + oauthProperties.getKakaoClientId()
                + "&redirect_uri="
                + oauthProperties.getKakaoRedirectUrl()
                + "&response_type=code";
    }

    public KakaoTokenResponse getOauthToken(String code) {
        return kakaoOauthClient.kakaoAuth(
                oauthProperties.getKakaoClientId(),
                oauthProperties.getKakaoRedirectUrl(),
                code,
                oauthProperties.getKakaoClientSecret());
    }

    public OauthUserInfo getUserInfo(String oauthAccessToken) {
        KakaoInformationResponse response =
                kakaoInfoClient.kakaoUserInfo(BEARER + oauthAccessToken);

        KakaoInformationResponse.KakaoAccount kakaoAccount = response.getKakaoAccount();
        return OauthUserInfo.of(
                response.getId(),
                kakaoAccount.getProfileImageUrl(),
                kakaoAccount.getIsDefaultImage(),
                kakaoAccount.getNickname(),
                OauthProvider.KAKAO);
    }

    public OIDCDecodePayload getOIDCDecodePayload(String token) {
        OIDCPublicKeysResponse oidcPublicKeysResponse = kakaoOauthClient.kakaoOIDCOpenKeys();
        return oauthOIDCHelper.getPayloadFromIdToken(
                token,
                oauthProperties.getKakaoBaseUrl(),
                oauthProperties.getKakaoAppId(),
                oidcPublicKeysResponse);
    }

    public OauthInfo getOauthInfoByIdToken(String idToken) {
        OIDCDecodePayload oidcDecodePayload = getOIDCDecodePayload(idToken);
        return OauthInfo.builder()
                .oauthId(oidcDecodePayload.getSub())
                .provider(OauthProvider.KAKAO)
                .build();
    }

    public void unlink(String oid) {
        String kakaoAdminKey = oauthProperties.getKakaoAdminKey();
        UnlinkKaKaoTarget unlinkKaKaoTarget = UnlinkKaKaoTarget.from(oid);
        String header = "KakaoAK " + kakaoAdminKey;
        kakaoInfoClient.unlinkUser(header, unlinkKaKaoTarget);
    }
}
