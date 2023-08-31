package com.todaysfail.api.web.category.dto.response;

public record CategoryResponse(
        Long categoryId, Long userId, String categoryName, CategoryColorResponse categoryColor) {}
