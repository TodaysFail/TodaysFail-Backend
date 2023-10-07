package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.exception.CategoryNotOwnedByUserException;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.usecase.CategoryModifyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryModifyService implements CategoryModifyUseCase {
    private final CategoryCommandPort categoryCommandPort;

    @Override
    public Category execute(Command command) {
        Category category = categoryCommandPort.queryCategory(command.categoryId());
        validationOwner(command, category);
        category.modify(command.categoryName(), command.categoryColor());
        return category;
    }

    private void validationOwner(Command command, Category category) {
        if (category.getUserId() != command.userId()) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
