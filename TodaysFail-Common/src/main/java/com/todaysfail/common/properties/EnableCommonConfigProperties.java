package com.todaysfail.common.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({OauthProperties.class, JwtProperties.class})
@Configuration
public class EnableCommonConfigProperties {}
