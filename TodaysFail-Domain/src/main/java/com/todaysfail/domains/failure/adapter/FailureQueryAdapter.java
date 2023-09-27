package com.todaysfail.domains.failure.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import com.todaysfail.domains.failure.repository.FailureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FailureQueryAdapter implements FailureQueryPort {
    private final FailureRepository failureRepository;

    @Override
    public Slice<Failure> queryFeed(Pageable pageable) {
        return failureRepository.findAllBySecretFalseOrderByFailureDateDesc(pageable);
    }
}
