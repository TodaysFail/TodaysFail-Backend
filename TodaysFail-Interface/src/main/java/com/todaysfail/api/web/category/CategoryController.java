package com.todaysfail.api.web.category;

import com.todaysfail.api.web.category.dto.request.CategoryRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.usecase.CategoryRegisterUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "5-1. [카테고리]")
@RestController
@RequestMapping("/api/v1/categories")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryMapper categoryMapper;
    private final CategoryRegisterUseCase categoryRegisterUseCase;

    @PostMapping
    public CategoryResponse registerCategory(@RequestBody @Valid CategoryRegisterRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        Category category =
                categoryRegisterUseCase.execute(
                        new CategoryRegisterUseCase.Command(
                                userId, request.categoryName(), request.categoryColorId()));
        return categoryMapper.toCategoryResponse(category);
    }
}
