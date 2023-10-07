package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.Category;
import java.util.List;

public interface CategoryQueryPort {
    Category queryCategory(Long categoryId);

    List<Category> queryCategoryByUserId(Long userId);
}
