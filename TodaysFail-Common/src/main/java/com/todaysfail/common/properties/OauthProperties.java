package com.todaysfail.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "oauth")
@ConstructorBinding
public class OauthProperties {

    private OAuthSecret kakao;

    @Getter
    @Setter
    public static class OAuthSecret {
        private String baseUrl;
        private String clientId;
        private String clientSecret;
        private String redirectUrl;
        private String appId;
        private String adminKey;
    }

    public String getKakaoBaseUrl() {
        return kakao.getBaseUrl();
    }

    public String getKakaoClientId() {
        return kakao.getClientId();
    }

    public String getKakaoRedirectUrl() {
        return kakao.getRedirectUrl();
    }

    public String getKakaoClientSecret() {
        return kakao.getClientSecret();
    }

    public String getKakaoAppId() {
        return kakao.getAppId();
    }

    public String getKakaoAdminKey() {
        return kakao.getAdminKey();
    }
}
