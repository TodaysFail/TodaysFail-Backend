package com.todaysfail;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackageClasses = {
            TodaysFailDomainApplication.class,
            TodaysFailInfrastructureApplication.class
        })
public class DomainIntegrateTestConfig {}
