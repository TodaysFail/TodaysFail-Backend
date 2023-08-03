package com.todaysfail.domains.auth.helper;

import com.todaysfail.common.annotation.Helper;
import com.todaysfail.common.dto.OIDCDecodePayload;
import com.todaysfail.common.jwt.JwtOIDCProvider;
import com.todaysfail.outer.api.oauth.dto.OIDCPublicKeyDto;
import com.todaysfail.outer.api.oauth.dto.OIDCPublicKeysResponse;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class OauthOIDCHelper {
    private final JwtOIDCProvider jwtOIDCProvider;

    private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
        return jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, iss, aud);
    }

    public OIDCDecodePayload getPayloadFromIdToken(
            String token, String iss, String aud, OIDCPublicKeysResponse oidcPublicKeysResponse) {
        String kid = getKidFromUnsignedIdToken(token, iss, aud);

        OIDCPublicKeyDto oidcPublicKeyDto =
                oidcPublicKeysResponse.getKeys().stream()
                        .filter(o -> o.kid().equals(kid))
                        .findFirst()
                        .orElseThrow();

        return jwtOIDCProvider.getOIDCTokenBody(token, oidcPublicKeyDto.n(), oidcPublicKeyDto.e());
    }
}
