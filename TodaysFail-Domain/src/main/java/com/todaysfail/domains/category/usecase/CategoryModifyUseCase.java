package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.Category;

public interface CategoryModifyUseCase {
    Category execute(Command command);

    record Command(Long userId, Long categoryId, String categoryName, Long categoryColorId) {}
}
