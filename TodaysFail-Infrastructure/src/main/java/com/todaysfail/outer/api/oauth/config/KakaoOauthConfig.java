package com.todaysfail.outer.api.oauth.config;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(OauthErrorDecoder.class)
public class KakaoOauthConfig {
    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public OauthErrorDecoder commonFeignErrorDecoder() {
        return new OauthErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
