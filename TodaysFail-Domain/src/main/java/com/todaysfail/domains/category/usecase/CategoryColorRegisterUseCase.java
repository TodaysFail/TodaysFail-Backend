package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.CategoryColor;

public interface CategoryColorRegisterUseCase {
    CategoryColor execute(Command command);

    record Command(String colorCode, String colorName) {}
}
