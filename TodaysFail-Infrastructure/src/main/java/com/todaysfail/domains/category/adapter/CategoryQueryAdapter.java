package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.category.repository.CategoryRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryAdapter implements CategoryQueryPort {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<CategoryEntity> queryCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
}
