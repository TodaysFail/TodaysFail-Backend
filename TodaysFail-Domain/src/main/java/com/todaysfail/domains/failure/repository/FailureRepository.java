package com.todaysfail.domains.failure.repository;

import com.todaysfail.domains.failure.domain.Failure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<Failure, Long> {}
