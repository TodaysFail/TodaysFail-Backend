package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.port.CategoryCommandPort;
import com.todaysfail.domains.category.repository.CategoryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class CategoryCommandAdapter implements CategoryCommandPort {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Optional<CategoryEntity> queryCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    @Override
    public void delete(CategoryEntity categoryEntity) {
        categoryRepository.delete(categoryEntity);
    }
}
