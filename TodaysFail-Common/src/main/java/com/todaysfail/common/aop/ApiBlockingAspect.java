package com.todaysfail.common.aop;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.exception.TodaysFailDynamicException;
import com.todaysfail.common.helper.SpringEnvironmentHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class ApiBlockingAspect {

    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Around("@annotation(com.todaysfail.common.annotation.DevelopOnly)")
    public Object checkApiAcceptingCondition(ProceedingJoinPoint joinPoint) throws Throwable {
        if (springEnvironmentHelper.isProdProfile()) {
            throw new TodaysFailDynamicException(
                    METHOD_NOT_ALLOWED, "Blocked API", "PRODUCTION 환경에서 사용할 수 없는 API 입니다.");
        }
        return joinPoint.proceed();
    }
}
