package com.todaysfail.domains.failure.port;

import com.todaysfail.domains.failure.domain.Failure;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FailureQueryPort {
    Slice<Failure> queryFeed(Pageable pageable);
}
