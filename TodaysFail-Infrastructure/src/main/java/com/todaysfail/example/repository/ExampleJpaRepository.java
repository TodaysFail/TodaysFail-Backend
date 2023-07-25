package com.todaysfail.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todaysfail.example.ExampleEntity;

public interface ExampleJpaRepository extends JpaRepository<ExampleEntity, Long> {
}
