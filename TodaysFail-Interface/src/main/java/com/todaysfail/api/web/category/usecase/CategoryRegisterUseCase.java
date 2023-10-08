package com.todaysfail.api.web.category.usecase;

import com.todaysfail.api.web.category.dto.request.CategoryRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.service.CategoryDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CategoryRegisterUseCase {
    private final CategoryMapper categoryMapper;
    private final CategoryDomainService categoryDomainService;

    public CategoryResponse execute(final CategoryRegisterRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();

        Category category =
                Category.builder()
                        .userId(userId)
                        .name(request.categoryName())
                        .categoryColor(request.categoryColor())
                        .build();

        Category savedCategory = categoryDomainService.register(category);
        return categoryMapper.toCategoryResponse(savedCategory);
    }
}
