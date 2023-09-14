package com.todaysfail.domains.category.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.repository.CategoryColorRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryColorQueryAdapter implements CategoryColorQueryPort {
    private final CategoryColorRepository categoryColorRepository;

    @Override
    public Optional<CategoryColor> queryCategoryColor(Long categoryColorId) {
        return categoryColorRepository.findById(categoryColorId);
    }

    @Override
    public List<CategoryColor> queryAll() {
        return categoryColorRepository.findAll();
    }
}