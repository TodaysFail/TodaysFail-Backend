package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.exception.CategoryColorNotFoundException;
import com.todaysfail.domains.category.exception.CategoryNotFoundException;
import com.todaysfail.domains.category.exception.CategoryNotOwnedByUserException;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.usecase.CategoryModifyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryModifyService implements CategoryModifyUseCase {
    private final CategoryCommandPort categoryCommandPort;
    private final CategoryColorQueryPort categoryColorQueryPort;

    @Override
    public Category execute(Command command) {
        CategoryEntity categoryEntity =
                categoryCommandPort
                        .queryCategory(command.categoryId())
                        .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
        CategoryColorEntity categoryColorEntity =
                categoryColorQueryPort
                        .queryCategoryColor(command.categoryColorId())
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        validationOwner(command, categoryEntity);
        categoryEntity.modify(command.categoryName(), command.categoryColorId());
        return Category.of(categoryEntity, categoryColorEntity);
    }

    private void validationOwner(Command command, CategoryEntity categoryEntity) {
        if (categoryEntity.getUserId() != command.userId()) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
