package com.todaysfail.api.web.category.usecase;

import com.todaysfail.api.web.category.dto.request.CategoryModifyRequest;
import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.service.CategoryDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CategoryModifyUseCase {
    private final CategoryMapper categoryMapper;
    private final CategoryDomainService categoryDomainService;

    public CategoryResponse execute(final Long categoryId, final CategoryModifyRequest request) {
        final Long userId = SecurityUtils.getCurrentUserId();
        Category category =
                categoryDomainService.modify(
                        userId, categoryId, request.categoryName(), request.categoryColor());
        return categoryMapper.toCategoryResponse(category);
    }
}
