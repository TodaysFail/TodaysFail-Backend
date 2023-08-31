package com.todaysfail.api.web.category.mapper;

import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.category.domain.CategoryColor;

@Mapper
public class CategoryColorMapper {
    public CategoryColorResponse toCategoryColorResponse(CategoryColor categoryColor) {
        return new CategoryColorResponse(
                categoryColor.getCategoryColorId(),
                categoryColor.getColorCode(),
                categoryColor.getColorName());
    }
}
