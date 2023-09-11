package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.repository.CategoryColorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class CategoryColorCommandAdapter implements CategoryColorCommandPort {
    private final CategoryColorRepository categoryColorRepository;

    @Override
    public CategoryColorEntity save(CategoryColorEntity categoryColorEntity) {
        return categoryColorRepository.save(categoryColorEntity);
    }
}
