package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.domain.CategoryColor;
import java.util.Optional;

public interface CategoryColorCommandPort {
    CategoryColor save(CategoryColor categoryColor);

    Optional<CategoryColor> queryCategoryColor(Long categoryColorId);

    void delete(CategoryColor categoryColor);
}
