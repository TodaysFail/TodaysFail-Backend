package com.todaysfail.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "oauth")
@ConstructorBinding
public record OauthProperties(OAuthSecret kakao) {
    record OAuthSecret(
            String baseUrl,
            String clientId,
            String clientSecret,
            String redirectUrl,
            String appId) {}
}
