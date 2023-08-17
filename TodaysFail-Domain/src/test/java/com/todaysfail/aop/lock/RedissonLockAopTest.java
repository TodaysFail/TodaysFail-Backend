package com.todaysfail.aop.lock;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import com.todaysfail.DomainIntegrateSpringBootTest;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@DomainIntegrateSpringBootTest
class RedissonLockAopTest {
    private OrderService orderService;

    class OrderService {
        private int stock = 100;

        @RedissonLock(identifier = "id", lockName = "stock")
        public void decreaseStock(int id) {
            stock -= 1;
        }
    }

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void 분산락_적용시() throws InterruptedException {
        // given
        // when
        AtomicLong successCount = new AtomicLong();
        CunCurrencyExecutorHelper.execute(() -> orderService.decreaseStock(1), successCount);
        int remain = 100 - successCount.intValue();

        // then
        assertThat(orderService.stock).isEqualTo(remain);
    }
}
