package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.Category;

public interface CategoryDeleteUseCase {
    Category execute(Command command);

    record Command(Long userId, Long categoryId) {}
}
