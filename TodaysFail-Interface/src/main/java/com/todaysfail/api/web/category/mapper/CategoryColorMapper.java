package com.todaysfail.api.web.category.mapper;

import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.category.domain.CategoryColor;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class CategoryColorMapper {
    public CategoryColorResponse toCategoryColorResponse(CategoryColor categoryColor) {
        return new CategoryColorResponse(
                categoryColor.getCategoryColorId(),
                categoryColor.getColorCode(),
                categoryColor.getColorName());
    }

    public List<CategoryColorResponse> toCategoryColorResponse(
            List<CategoryColor> categoryColorList) {
        return categoryColorList.stream()
                .map(this::toCategoryColorResponse)
                .collect(Collectors.toList());
    }
}
