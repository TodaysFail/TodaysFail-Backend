package com.todaysfail.domains.category.repository;

import com.todaysfail.domains.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {}
