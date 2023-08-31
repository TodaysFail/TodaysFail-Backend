package com.todaysfail.domains.failure.repository;

import com.todaysfail.domains.failure.entity.FailureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailureRepository extends JpaRepository<FailureEntity, Long> {}
