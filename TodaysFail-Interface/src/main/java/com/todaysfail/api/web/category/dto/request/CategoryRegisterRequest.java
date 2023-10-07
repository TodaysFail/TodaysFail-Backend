package com.todaysfail.api.web.category.dto.request;

import com.todaysfail.domains.category.domain.CategoryColor;

public record CategoryRegisterRequest(String categoryName, CategoryColor categoryColor) {}
