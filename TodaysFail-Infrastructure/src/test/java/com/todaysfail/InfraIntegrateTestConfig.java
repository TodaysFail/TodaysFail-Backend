package com.todaysfail;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.todaysfail.common.TodaysFailCommonApplication;

@Configuration
@ComponentScan(basePackageClasses = {TodaysFailInfrastructureApplication.class, TodaysFailCommonApplication.class})
public class InfraIntegrateTestConfig {}
