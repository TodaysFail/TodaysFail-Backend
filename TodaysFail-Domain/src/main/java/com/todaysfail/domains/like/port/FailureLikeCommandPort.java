package com.todaysfail.domains.like.port;

import com.todaysfail.domains.like.domain.FailureLike;

public interface FailureLikeCommandPort {
    FailureLike save(FailureLike failureLikee);
}
