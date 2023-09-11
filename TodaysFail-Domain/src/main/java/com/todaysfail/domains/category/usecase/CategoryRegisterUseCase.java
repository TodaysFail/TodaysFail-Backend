package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.Category;

public interface CategoryRegisterUseCase {
    Category execute(Command command);

    record Command(Long userId, String categoryName, Long categoryColorId) {}
}
