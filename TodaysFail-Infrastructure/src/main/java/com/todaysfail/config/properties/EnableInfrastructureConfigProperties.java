package com.todaysfail.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(ReplicationDataSourceProperties.class)
@Configuration
public class EnableInfrastructureConfigProperties {}
