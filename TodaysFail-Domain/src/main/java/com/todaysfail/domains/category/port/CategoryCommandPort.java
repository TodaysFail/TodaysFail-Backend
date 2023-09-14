package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.Category;
import java.util.Optional;

public interface CategoryCommandPort {
    Category save(Category categoryEntity);

    Optional<Category> queryCategory(Long categoryId);

    void delete(Category categoryEntity);
}
