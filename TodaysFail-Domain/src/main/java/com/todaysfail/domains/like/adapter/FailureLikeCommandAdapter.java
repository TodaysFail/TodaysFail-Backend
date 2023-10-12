package com.todaysfail.domains.like.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.like.domain.FailureLike;
import com.todaysfail.domains.like.port.FailureLikeCommandPort;
import com.todaysfail.domains.like.repository.FailureLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class FailureLikeCommandAdapter implements FailureLikeCommandPort {
    private final FailureLikeRepository failureLikeRepository;

    @Override
    public FailureLike save(FailureLike failureLike) {
        return failureLikeRepository.save(failureLike);
    }
}
