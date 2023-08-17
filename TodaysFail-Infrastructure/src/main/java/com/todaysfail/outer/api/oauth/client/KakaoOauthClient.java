package com.todaysfail.outer.api.oauth.client;

import com.todaysfail.outer.api.oauth.config.KakaoOauthConfig;
import com.todaysfail.outer.api.oauth.dto.KakaoTokenResponse;
import com.todaysfail.outer.api.oauth.dto.OIDCPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "KakaoOAuthClient",
        url = "${feign.kakao.oauth}",
        configuration = KakaoOauthConfig.class)
public interface KakaoOauthClient {
    @PostMapping(
            "/oauth/token?grant_type=authorization_code&client_id={CLIENT_ID}&redirect_uri={REDIRECT_URI}&code={CODE}&client_secret={CLIENT_SECRET}")
    KakaoTokenResponse kakaoAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("REDIRECT_URI") String redirectUri,
            @PathVariable("CODE") String code,
            @PathVariable("CLIENT_SECRET") String client_secret);

    @GetMapping("/.well-known/jwks.json")
    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "oidcCacheManager")
    OIDCPublicKeysResponse kakaoOIDCOpenKeys();
}
