package com.todaysfail.domains.category.service;

import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.port.CategoryColorCommandPort;
import com.todaysfail.domains.category.usecase.CategoryColorRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryColorRegisterService implements CategoryColorRegisterUseCase {
    private final CategoryColorCommandPort categoryColorCommandPort;

    @Override
    public CategoryColor execute(Command command) {
        return categoryColorCommandPort.save(
                CategoryColor.builder()
                        .colorCode(command.colorCode())
                        .colorName(command.colorName())
                        .build());
    }
}
