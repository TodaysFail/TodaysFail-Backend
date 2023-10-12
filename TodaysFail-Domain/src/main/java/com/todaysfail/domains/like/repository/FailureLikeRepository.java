package com.todaysfail.domains.like.repository;

import com.todaysfail.domains.like.domain.FailureLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureLikeRepository extends JpaRepository<FailureLike, Long> {
    Optional<FailureLike> findByUserIdAndFailureId(Long userId, Long failureId);
}
