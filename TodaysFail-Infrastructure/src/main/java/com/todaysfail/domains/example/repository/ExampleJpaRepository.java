package com.todaysfail.domains.example.repository;

import com.todaysfail.domains.example.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleJpaRepository extends JpaRepository<ExampleEntity, Long> {}