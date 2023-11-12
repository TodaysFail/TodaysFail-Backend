package com.todaysfail.events.handler;

import static com.todaysfail.common.consts.TodaysFailConst.RECOMMEND_TAG_KEY;

import com.todaysfail.common.annotation.EventHandler;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.events.FailureRegisterEvent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@EventHandler
@RequiredArgsConstructor
public class FailureRegisterEventHandler {
    private final TagQueryPort tagQueryPort;
    private final RedisTemplate<String, String> redisTemplate;

    @Async
    @TransactionalEventListener(
            classes = FailureRegisterEvent.class,
            phase = TransactionPhase.AFTER_COMMIT)
    public void handleUserRegisterEvent(FailureRegisterEvent event) {
        final Failure failure = event.getFailure();
        log.info("[DOMAIN EVENT : FailureRegisterEvent] failureId: {}", failure.getId());

        List<Long> tagIds = failure.getTags();
        List<Tag> tags = tagQueryPort.queryAllByIds(tagIds);

        tags.stream()
                .forEach(
                        tag -> {
                            redisTemplate
                                    .opsForZSet()
                                    .incrementScore(RECOMMEND_TAG_KEY, tag.getTagName(), 1);
                        });
    }
}
