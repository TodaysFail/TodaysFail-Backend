package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryColorEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryColorQueryPort {
    Optional<CategoryColorEntity> queryCategoryColor(Long categoryColorId);

    List<CategoryColorEntity> queryAll();
}
