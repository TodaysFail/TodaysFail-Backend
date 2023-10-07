package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.domain.CategoryColor;

public interface CategoryRegisterUseCase {
    Category execute(Command command);

    record Command(Long userId, String categoryName, CategoryColor categoryColor) {}
}
