package com.todaysfail.domains.category.port;

import com.todaysfail.domains.category.entity.CategoryColorEntity;

public interface CategoryColorCommandPort {
    CategoryColorEntity save(CategoryColorEntity categoryColorEntity);
}
