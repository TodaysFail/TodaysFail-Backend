package com.todaysfail.domains.failure.service;

import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import com.todaysfail.domains.failure.usecase.FailureFeedQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FailureFeedQueryService implements FailureFeedQueryUseCase {
    private final FailureQueryPort failureQueryPort;

    @Override
    public Slice<Failure> execute(Query query) {
        return failureQueryPort.queryFeed(query.pageable());
    }
}
