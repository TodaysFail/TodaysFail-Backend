package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.domain.CategoryColor;
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
        CategoryColor categoryColor =
                categoryColorQueryPort
                        .queryCategoryColor(command.categoryColorId())
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        return categoryCommandPort.save(
                Category.builder()
                        .userId(command.userId())
                        .name(command.categoryName())
                        .categoryColor(categoryColor)
                        .build());
    }
}
