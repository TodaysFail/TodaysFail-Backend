package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.usecase.CategoryColorQueryUseCase;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryColorQueryService implements CategoryColorQueryUseCase {
    private final CategoryColorQueryPort categoryColorQueryPort;

    @Override
    public List<CategoryColor> execute() {
        List<CategoryColorEntity> categoryColorEntities = categoryColorQueryPort.queryAll();
        return categoryColorEntities.stream()
                .map(
                        categoryColorEntity ->
                                CategoryColor.of(
                                        categoryColorEntity.getId(),
                                        categoryColorEntity.getColorName(),
                                        categoryColorEntity.getColorCode()))
                .collect(Collectors.toList());
    }
}
