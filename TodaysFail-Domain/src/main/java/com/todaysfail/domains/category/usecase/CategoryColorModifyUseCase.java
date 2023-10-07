package com.todaysfail.domains.category.usecase;

public interface CategoryColorModifyUseCase {
    CategoryColor execute(final Command command);

    record Command(Long categoryColorId, String colorCode, String colorName) {}
}
