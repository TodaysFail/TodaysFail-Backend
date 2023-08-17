package com.todaysfail.aop.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.function.Executable;

@Slf4j
public class CunCurrencyExecutorHelper {
    static int numberOfThreads = 10;
    static int numberOfThreadPool = 5;

    public static void execute(Executable executable, AtomicLong successCount)
            throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreadPool);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        for (long i = 1; i <= numberOfThreads; i++) {
            executorService.submit(
                    () -> {
                        try {
                            executable.execute();
                            successCount.getAndIncrement();
                        } catch (Throwable e) {
                            log.info(e.getClass().getName());
                        } finally {
                            latch.countDown();
                        }
                    });
        }
        latch.await();
    }
}
