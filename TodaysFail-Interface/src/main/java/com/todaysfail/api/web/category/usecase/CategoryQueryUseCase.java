package com.todaysfail.api.web.category.usecase;

import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CategoryQueryUseCase {
    private final CategoryMapper categoryMapper;
    private final CategoryQueryPort categoryQueryPort;

    public List<CategoryResponse> execute() {
        final Long userId = SecurityUtils.getCurrentUserId();
        List<Category> categories = categoryQueryPort.queryCategoryByUserId(userId);
        return categoryMapper.toCategoryResponses(categories);
    }
}
