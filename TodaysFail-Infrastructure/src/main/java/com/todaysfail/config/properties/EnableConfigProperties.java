package com.todaysfail.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({OauthProperties.class})
@Configuration
public class EnableConfigProperties {}
