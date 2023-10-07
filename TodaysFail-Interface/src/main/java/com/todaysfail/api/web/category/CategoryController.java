package com.todaysfail.api.web.category;

import com.todaysfail.api.web.category.dto.request.CategoryModifyRequest;
import com.todaysfail.api.web.category.dto.request.CategoryRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.usecase.CategoryDeleteUseCase;
import com.todaysfail.domains.category.usecase.CategoryModifyUseCase;
import com.todaysfail.domains.category.usecase.CategoryQueryUseCase;
import com.todaysfail.domains.category.usecase.CategoryRegisterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final CategoryQueryUseCase categoryQueryUseCase;
    private final CategoryModifyUseCase categoryModifyUseCase;
    private final CategoryDeleteUseCase categoryDeleteUseCase;

    @Operation(summary = "카테고리 등록")
    @PostMapping
    public CategoryResponse registerCategory(@RequestBody @Valid CategoryRegisterRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        Category category =
                categoryRegisterUseCase.execute(
                        new CategoryRegisterUseCase.Command(
                                userId, request.categoryName(), request.categoryColor()));
        return categoryMapper.toCategoryResponse(category);
    }

    @Operation(summary = "자신의 카테고리 조회")
    @GetMapping("/me")
    public List<CategoryResponse> myCategoryQueryAll() {
        Long userId = SecurityUtils.getCurrentUserId();
        List<Category> categoryList = categoryQueryUseCase.execute(userId);
        return categoryMapper.toCategoryResponse(categoryList);
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    public CategoryResponse modifyCategory(
            @PathVariable Long categoryId, @RequestBody @Valid CategoryModifyRequest request) {
        Long userId = SecurityUtils.getCurrentUserId();
        Category category =
                categoryModifyUseCase.execute(
                        new CategoryModifyUseCase.Command(
                                userId,
                                categoryId,
                                request.categoryName(),
                                request.categoryColor()));
        return categoryMapper.toCategoryResponse(category);
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        Long userId = SecurityUtils.getCurrentUserId();
        categoryDeleteUseCase.execute(new CategoryDeleteUseCase.Command(userId, categoryId));
    }

    @Operation(summary = "카테고리 컬러 조회")
    @GetMapping
    public List<CategoryColor> categoryColorQueryAll() {
        return Arrays.stream(CategoryColor.values()).collect(Collectors.toList());
    }
}
