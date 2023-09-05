package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryEntity;

public interface CategoryCommandPort {
    CategoryEntity save(CategoryEntity categoryEntity);
}
