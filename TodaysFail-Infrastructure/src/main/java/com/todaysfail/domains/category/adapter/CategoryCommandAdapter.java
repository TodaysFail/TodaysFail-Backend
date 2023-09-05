package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.entity.CategoryEntity;
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
    public CategoryEntity save(CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }
}
