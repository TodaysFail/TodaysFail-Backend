package com.todaysfail.domains.failure.port;

import com.todaysfail.domains.failure.domain.Failure;

public interface FailureCommandPort {
    Failure save(Failure failure);

    Failure queryFailure(Long failureId);
}
