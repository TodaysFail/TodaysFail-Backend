package com.todaysfail.domains.like.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.like.domain.FailureLike;
import com.todaysfail.domains.like.exception.FailureLikeAlreadyLikedException;
import com.todaysfail.domains.like.port.FailureLikeQueryPort;
import com.todaysfail.domains.like.repository.FailureLikeRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FailureLikeQueryAdapter implements FailureLikeQueryPort {
    private final FailureLikeRepository failureLikeRepository;

    @Override
    public void checkAlreadyLiked(Long userId, Long failureId) {
        Optional<FailureLike> failureLikeOptional =
                failureLikeRepository.findByUserIdAndFailureId(userId, failureId);
        if (failureLikeOptional.isPresent()) {
            throw FailureLikeAlreadyLikedException.EXCEPTION;
        }
    }
}
