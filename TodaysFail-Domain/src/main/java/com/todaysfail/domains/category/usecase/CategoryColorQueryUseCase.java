package com.todaysfail.domains.category.usecase;

import com.todaysfail.domains.category.domain.CategoryColor;
import java.util.List;

public interface CategoryColorQueryUseCase {
    List<CategoryColor> execute();
}
