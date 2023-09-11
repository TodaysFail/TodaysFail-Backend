package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.CategoryColor;

public interface CategoryColorModifyUseCase {
    CategoryColor execute(Command command);

    record Command(Long categoryColorId, String colorCode, String colorName) {}
}
