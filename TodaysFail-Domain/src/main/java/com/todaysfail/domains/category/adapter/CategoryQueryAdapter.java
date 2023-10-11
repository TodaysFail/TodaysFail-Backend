package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.exception.CategoryNotFoundException;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.category.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryAdapter implements CategoryQueryPort {
    private final CategoryRepository categoryRepository;

    @Override
    public Category queryCategory(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    @Override
    public List<Category> queryCategoryByUserId(Long userId) {
        return categoryRepository.findAllByUserId(userId);
    }
}
