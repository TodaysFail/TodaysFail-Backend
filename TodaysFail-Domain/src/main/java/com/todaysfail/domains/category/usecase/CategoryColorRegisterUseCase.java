package com.todaysfail.domains.category.usecase;

public interface CategoryColorRegisterUseCase {
    CategoryColor execute(final Command command);

    record Command(String colorCode, String colorName) {}
}
