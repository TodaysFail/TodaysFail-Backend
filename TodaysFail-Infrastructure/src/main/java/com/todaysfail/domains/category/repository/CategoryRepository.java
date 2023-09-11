package com.todaysfail.domains.category.repository;

import com.todaysfail.domains.category.entity.CategoryEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByUserId(Long userId);
}
