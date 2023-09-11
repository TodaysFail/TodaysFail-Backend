package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.category.usecase.CategoryQueryUseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryService implements CategoryQueryUseCase {
    private final CategoryQueryPort categoryQueryPort;
    private final CategoryColorQueryPort categoryColorQueryPort;

    @Override
    public List<Category> execute(Long userId) {
        List<CategoryEntity> categoryEntities = categoryQueryPort.queryCategoryByUserId(userId);
        return categoryEntities.stream()
                .map(
                        categoryEntity -> {
                            CategoryColorEntity categoryColorEntity =
                                    categoryColorQueryPort
                                            .queryCategoryColor(categoryEntity.getCategoryColorId())
                                            .orElseThrow(
                                                    () ->
                                                            new IllegalArgumentException(
                                                                    "존재하지 않는 카테고리 색상입니다."));
                            return Category.of(categoryEntity, categoryColorEntity);
                        })
                .collect(Collectors.toList());
    }
}
