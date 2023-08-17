package com.todaysfail.common.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "auth.jwt")
@ConstructorBinding
public class JwtProperties {
    private String secretKey;
    private Long accessExp;
    private Long refreshExp;
}
