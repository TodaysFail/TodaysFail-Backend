package com.todaysfail.api.web.category.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.service.CategoryDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CategoryDeleteUseCase {
    private final CategoryDomainService categoryDomainService;

    public void execute(final Long categoryId) {
        final Long userId = SecurityUtils.getCurrentUserId();
        categoryDomainService.delete(categoryId, userId);
    }
}
