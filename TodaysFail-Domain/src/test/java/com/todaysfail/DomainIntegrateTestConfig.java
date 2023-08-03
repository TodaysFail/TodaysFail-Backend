package com.todaysfail;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.todaysfail.common.TodaysFailCommonApplication;

@Configuration
@ComponentScan(
        basePackageClasses = {
            TodaysFailDomainApplication.class,
            TodaysFailInfrastructureApplication.class,
            TodaysFailCommonApplication.class
        })
public class DomainIntegrateTestConfig {}
