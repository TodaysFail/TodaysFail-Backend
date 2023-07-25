package com.todaysfail.example.repository;

import com.todaysfail.example.ExampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleJpaRepository extends JpaRepository<ExampleEntity, Long> {}
