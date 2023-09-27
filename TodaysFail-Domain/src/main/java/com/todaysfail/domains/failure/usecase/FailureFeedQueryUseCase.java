package com.todaysfail.domains.failure.usecase;

import com.todaysfail.domains.failure.domain.Failure;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FailureFeedQueryUseCase {
    Slice<Failure> execute(Query query);

    record Query(Pageable pageable) {}
}
