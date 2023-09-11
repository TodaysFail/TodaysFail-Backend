package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryEntity;
import java.util.Optional;

public interface CategoryCommandPort {
    CategoryEntity save(CategoryEntity categoryEntity);

    Optional<CategoryEntity> queryCategory(Long categoryId);

    void delete(CategoryEntity categoryEntity);
}
