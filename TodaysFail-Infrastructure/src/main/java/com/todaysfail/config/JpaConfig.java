package com.todaysfail.config;

import com.todaysfail.PersistencePackage;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = {PersistencePackage.class})
public class JpaConfig {}
