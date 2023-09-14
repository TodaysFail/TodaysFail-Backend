package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.exception.CategoryColorNotFoundException;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.usecase.CategoryColorModifyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryColorModifyService implements CategoryColorModifyUseCase {
    private final CategoryColorCommandPort categoryColorCommandPort;

    @Override
    @Transactional
    public CategoryColor execute(Command command) {
        CategoryColor categoryColor =
                categoryColorCommandPort
                        .queryCategoryColor(command.categoryColorId())
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        categoryColor.modify(command.colorCode(), command.colorName());
        return categoryColor;
    }
}
