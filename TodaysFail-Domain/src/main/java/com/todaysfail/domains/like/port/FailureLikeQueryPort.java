package com.todaysfail.domains.like.port;

public interface FailureLikeQueryPort {
    void checkAlreadyLiked(Long userId, Long failureId);
}
