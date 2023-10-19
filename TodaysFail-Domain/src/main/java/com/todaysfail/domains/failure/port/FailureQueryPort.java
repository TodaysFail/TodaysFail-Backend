package com.todaysfail.domains.failure.port;

import com.todaysfail.domains.failure.domain.Failure;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FailureQueryPort {
    Slice<Failure> queryFeed(Pageable pageable);

    List<Failure> queryFailureByUserId(Long userId);
}
