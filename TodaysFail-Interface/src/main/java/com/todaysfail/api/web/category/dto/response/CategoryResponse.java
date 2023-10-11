package com.todaysfail.api.web.category.dto.response;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.domain.CategoryColor;

public record CategoryResponse(
        Long categoryId, Long userId, String categoryName, CategoryColor categoryColor) {

    public static CategoryResponse from(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getUserId(),
                category.getName(),
                category.getCategoryColor());
    }
}
