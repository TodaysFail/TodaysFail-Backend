package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.CategoryColor;
import java.util.List;
import java.util.Optional;

public interface CategoryColorQueryPort {
    Optional<CategoryColor> queryCategoryColor(Long categoryColorId);

    List<CategoryColor> queryAll();
}
