package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.exception.CategoryNotFoundException;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class CategoryCommandAdapter implements CategoryCommandPort {
    private final CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category queryCategory(Long categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElseThrow(() -> CategoryNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
