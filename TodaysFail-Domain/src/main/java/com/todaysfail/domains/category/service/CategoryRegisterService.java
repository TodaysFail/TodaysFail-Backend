package com.todaysfail.domains.category.service;

import static com.todaysfail.domains.category.usecase.CategoryRegisterUseCase.*;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.exception.CategoryColorNotFoundException;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.usecase.CategoryRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryRegisterService implements CategoryRegisterUseCase {
    private final CategoryCommandPort categoryCommandPort;
    private final CategoryColorQueryPort categoryColorQueryPort;

    @Override
    @Transactional
    public Category execute(Command command) {
        CategoryColorEntity categoryColorEntity =
                categoryColorQueryPort
                        .queryCategoryColor(command.categoryColorId())
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        CategoryEntity categoryEntity =
                CategoryEntity.registerCategory(
                        command.userId(), command.categoryName(), categoryColorEntity.getId());
        CategoryEntity savedCategoryEntity = categoryCommandPort.save(categoryEntity);
        return Category.of(savedCategoryEntity, categoryColorEntity);
    }
}
