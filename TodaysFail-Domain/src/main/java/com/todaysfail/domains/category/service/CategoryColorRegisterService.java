package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.usecase.CategoryColorRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryColorRegisterService implements CategoryColorRegisterUseCase {
    private final CategoryColorCommandPort categoryColorCommandPort;

    @Override
    public CategoryColor execute(Command command) {
        CategoryColorEntity categoryColorEntity =
                CategoryColorEntity.registerCategoryColor(command.colorCode(), command.colorName());
        CategoryColorEntity savedCategoryColorEntity =
                categoryColorCommandPort.save(categoryColorEntity);
        return CategoryColor.of(
                savedCategoryColorEntity.getId(),
                savedCategoryColorEntity.getColorCode(),
                savedCategoryColorEntity.getColorName());
    }
}
