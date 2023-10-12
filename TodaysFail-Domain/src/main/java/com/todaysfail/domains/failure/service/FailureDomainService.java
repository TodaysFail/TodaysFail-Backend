package com.todaysfail.domains.failure.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.exception.FutureFailureDateException;
import com.todaysfail.domains.failure.port.FailureCommandPort;
import com.todaysfail.domains.like.domain.FailureLike;
import com.todaysfail.domains.like.port.FailureLikeCommandPort;
import com.todaysfail.domains.like.port.FailureLikeQueryPort;
import com.todaysfail.domains.tag.exception.TagCountExceedException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FailureDomainService {
    private final FailureCommandPort failureCommandPort;
    private final FailureLikeCommandPort failureLikeCommandPort;
    private final FailureLikeQueryPort failureLikeQueryPort;

    public Failure register(final Failure failure, Category category) {
        validateFailureTagSize(failure.getTags());
        validateFailureDate(failure.getFailureDate());
        category.validateOwnership(failure.getUserId());
        return failureCommandPort.save(failure);
    }

    private void validateFailureTagSize(List<Long> tags) {
        /** 태그는 최대 3개까지만 등록 가능하다. */
        if (tags.size() > 3) {
            throw TagCountExceedException.EXCEPTION;
        }
    }

    private void validateFailureDate(LocalDate date) {
        if (date.isAfter(LocalDate.now())) {
            throw FutureFailureDateException.EXCEPTION;
        }
    }

    @Transactional
    @RedissonLock(lockName = "실패좋아요", identifier = "failureId")
    public void likeFailure(Long userId, Long failureId) {
        Failure failure = failureCommandPort.queryFailure(failureId);
        failure.validateOwnership(userId);
        failure.like();
        failureLikeQueryPort.checkAlreadyLiked(userId, failureId);
        failureLikeCommandPort.save(FailureLike.of(userId, failureId));
    }
}
