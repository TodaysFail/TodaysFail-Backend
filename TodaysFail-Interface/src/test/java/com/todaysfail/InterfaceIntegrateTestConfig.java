package com.todaysfail;

import com.todaysfail.common.TodaysFailCommonApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackageClasses = {TodaysFailApplication.class, TodaysFailCommonApplication.class})
public class InterfaceIntegrateTestConfig {}
