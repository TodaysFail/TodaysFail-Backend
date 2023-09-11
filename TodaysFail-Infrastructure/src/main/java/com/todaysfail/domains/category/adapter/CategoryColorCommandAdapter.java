package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.repository.CategoryColorRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class CategoryColorCommandAdapter implements CategoryColorCommandPort {
    private final CategoryColorRepository categoryColorRepository;

    @Override
    public Optional<CategoryColorEntity> queryCategoryColor(Long categoryColorId) {
        return categoryColorRepository.findById(categoryColorId);
    }

    @Override
    public void delete(CategoryColorEntity categoryColorEntity) {
        categoryColorRepository.delete(categoryColorEntity);
    }

    @Override
    public CategoryColorEntity save(CategoryColorEntity categoryColorEntity) {
        return categoryColorRepository.save(categoryColorEntity);
    }
}
