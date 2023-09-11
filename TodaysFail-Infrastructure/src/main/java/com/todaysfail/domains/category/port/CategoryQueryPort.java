package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryQueryPort {
    Optional<CategoryEntity> queryCategory(Long categoryId);

    List<CategoryEntity> queryCategoryByUserId(Long userId);
}
