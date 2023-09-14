package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryQueryPort {
    Optional<Category> queryCategory(Long categoryId);

    List<Category> queryCategoryByUserId(Long userId);
}
