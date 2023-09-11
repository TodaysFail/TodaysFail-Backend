package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryColorEntity;
import java.util.Optional;

public interface CategoryColorCommandPort {
    CategoryColorEntity save(CategoryColorEntity categoryColorEntity);

    Optional<CategoryColorEntity> queryCategoryColor(Long categoryColorId);

    void delete(CategoryColorEntity categoryColorEntity);
}
