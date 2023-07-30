package com.todaysfail;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.test.context.TestPropertySource;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@TestPropertySource(properties = {"ableRedissonLock=false"})
@Documented
public @interface DisableRedissonLock {}
