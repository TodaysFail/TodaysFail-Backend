package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.Category;
import java.util.List;

public interface CategoryQueryUseCase {
    List<Category> execute(Long userId);
}
