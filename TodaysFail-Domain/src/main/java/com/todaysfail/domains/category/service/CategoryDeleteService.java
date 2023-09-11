package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.exception.CategoryNotFoundException;
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
    public Category execute(Command command) {
        CategoryEntity categoryEntity =
                categoryCommandPort
                        .queryCategory(command.categoryId())
                        .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
        validationOwner(command, categoryEntity);
        categoryCommandPort.delete(categoryEntity);
        return null;
    }

    private void validationOwner(Command command, CategoryEntity categoryEntity) {
        if (command.userId() != categoryEntity.getUserId()) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
