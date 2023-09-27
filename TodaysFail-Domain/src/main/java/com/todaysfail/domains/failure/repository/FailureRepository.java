package com.todaysfail.domains.failure.repository;

import com.todaysfail.domains.failure.domain.Failure;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    Slice<Failure> findAllBySecretFalseOrderByFailureDateDesc(Pageable pageable);
}
