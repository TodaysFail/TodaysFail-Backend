package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.Category;

public interface CategoryCommandPort {
    Category save(Category categoryEntity);

    Category queryCategory(Long categoryId);

    void delete(Category categoryEntity);
}
