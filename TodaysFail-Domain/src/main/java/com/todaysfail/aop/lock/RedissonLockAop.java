package com.todaysfail.aop.lock;

import com.todaysfail.common.exception.BadLockIdentifierException;
import com.todaysfail.common.exception.NotAvailableRedissonLockException;
import com.todaysfail.common.exception.TodaysFailCodeException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionTimedOutException;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
@ConditionalOnExpression("${ableRedissonLock:true}")
public class RedissonLockAop {
    private final RedissonClient redissonClient;
    private final RedissonCallNewTransaction redissonCallNewTransaction;

    @Around("@annotation(com.todaysfail.aop.lock.RedissonLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        RedissonLock redissonLock = method.getAnnotation(RedissonLock.class);
        String baseKey = redissonLock.lockName();

        String dynamicKey =
                getDynamicKeyFromMethodArg(
                        signature.getParameterNames(),
                        joinPoint.getArgs(),
                        redissonLock.identifier());

        RLock rLock = redissonClient.getLock(baseKey + ":" + dynamicKey);

        log.info("redisson 키 설정" + baseKey + ":" + dynamicKey);

        long waitTime = redissonLock.waitTime();
        long leaseTime = redissonLock.leaseTime();
        TimeUnit timeUnit = redissonLock.timeUnit();
        try {
            boolean available = rLock.tryLock(waitTime, leaseTime, timeUnit);
            if (!available) {
                throw NotAvailableRedissonLockException.EXCEPTION;
            }
            log.info(
                    "redisson 락 안으로 진입 "
                            + baseKey
                            + ":"
                            + dynamicKey
                            + "쓰레드 아이디"
                            + Thread.currentThread().getId());
            return redissonCallNewTransaction.proceed(joinPoint);
        } catch (TodaysFailCodeException | TransactionTimedOutException e) {
            throw e;
        } finally {
            try {
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.error(e + baseKey + dynamicKey);
                throw e;
            }
        }
    }

    public String getDynamicKeyFromMethodArg(
            String[] methodParameterNames, Object[] args, String paramName) {
        for (int i = 0; i < methodParameterNames.length; i++) {
            if (methodParameterNames[i].equals(paramName)) {
                return String.valueOf(args[i]);
            }
        }
        throw BadLockIdentifierException.EXCEPTION;
    }
}
