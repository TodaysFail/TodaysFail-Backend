package com.todaysfail.api.web.category.mapper;

import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.common.annotation.Mapper;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class CategoryColorMapper {
    public CategoryColorResponse toCategoryColorResponse(CategoryColor categoryColor) {
        return new CategoryColorResponse(
                categoryColor.getId(), categoryColor.getColorCode(), categoryColor.getColorName());
    }

    public List<CategoryColorResponse> toCategoryColorResponse(
            List<CategoryColor> categoryColorList) {
        return categoryColorList.stream()
                .map(this::toCategoryColorResponse)
                .collect(Collectors.toList());
    }
}
