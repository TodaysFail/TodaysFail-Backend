package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.exception.CategoryColorNotFoundException;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.usecase.CategoryColorDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryColorDeleteService implements CategoryColorDeleteUseCase {
    private final CategoryColorCommandPort categoryColorCommandPort;

    @Override
    public void delete(Long categoryColorId) {
        CategoryColorEntity categoryColorEntity =
                categoryColorCommandPort
                        .queryCategoryColor(categoryColorId)
                        .orElseThrow(() -> CategoryColorNotFoundException.EXCEPTION);
        categoryColorCommandPort.delete(categoryColorEntity);
    }
}
