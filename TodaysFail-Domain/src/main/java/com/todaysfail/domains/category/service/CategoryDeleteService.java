package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.exception.CategoryNotOwnedByUserException;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.usecase.CategoryDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryDeleteService implements CategoryDeleteUseCase {
    private final CategoryCommandPort categoryCommandPort;

    @Override
    public void execute(Command command) {
        Category category = categoryCommandPort.queryCategory(command.categoryId());
        validationOwner(command, category);
        categoryCommandPort.delete(category);
    }

    private void validationOwner(Command command, Category category) {
        if (command.userId() != category.getUserId()) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
