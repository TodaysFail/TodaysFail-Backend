package com.todaysfail.domains.category.usecase;

public interface CategoryDeleteUseCase {
    void execute(Command command);

    record Command(Long userId, Long categoryId) {}
}
