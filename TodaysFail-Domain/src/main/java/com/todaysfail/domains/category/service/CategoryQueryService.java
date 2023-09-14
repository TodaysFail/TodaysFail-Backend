package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.category.usecase.CategoryQueryUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryQueryService implements CategoryQueryUseCase {
    private final CategoryQueryPort categoryQueryPort;

    @Override
    public List<Category> execute(Long userId) {
        return categoryQueryPort.queryCategoryByUserId(userId);
    }
}
