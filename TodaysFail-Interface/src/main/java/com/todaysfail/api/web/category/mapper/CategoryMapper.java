package com.todaysfail.api.web.category.mapper;

import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.category.domain.Category;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.from(category);
    }

    public List<CategoryResponse> toCategoryResponses(List<Category> categoryList) {
        return categoryList.stream().map(this::toCategoryResponse).collect(Collectors.toList());
    }
}
