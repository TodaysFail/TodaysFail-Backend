package com.todaysfail.api.web.category.dto.request;

import com.todaysfail.domains.category.domain.CategoryColor;

public record CategoryModifyRequest(String categoryName, CategoryColor categoryColor) {}
