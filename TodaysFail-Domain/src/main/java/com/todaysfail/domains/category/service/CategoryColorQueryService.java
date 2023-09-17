package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.port.CategoryColorQueryPort;
import com.todaysfail.domains.category.usecase.CategoryColorQueryUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryColorQueryService implements CategoryColorQueryUseCase {
    private final CategoryColorQueryPort categoryColorQueryPort;

    @Override
    public List<CategoryColor> execute() {
        return categoryColorQueryPort.queryAll();
    }
}
