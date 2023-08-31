package com.todaysfail.api.web.category.mapper;

import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.category.domain.Category;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CategoryMapper {
    private final CategoryColorMapper categoryColorMapper;

    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(
                category.getCategoryId(),
                category.getUserId(),
                category.getCategoryName(),
                categoryColorMapper.toCategoryColorResponse(category.getCategoryColor()));
    }
}
