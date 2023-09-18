package com.todaysfail.api.web.admin;

import com.todaysfail.api.web.category.dto.request.CategoryColorModifyRequest;
import com.todaysfail.api.web.category.dto.request.CategoryColorRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.api.web.category.mapper.CategoryColorMapper;
import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.usecase.CategoryColorDeleteUseCase;
import com.todaysfail.domains.category.usecase.CategoryColorModifyUseCase;
import com.todaysfail.domains.category.usecase.CategoryColorRegisterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Z. [관리자]")
@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CategoryColorMapper categoryColorMapper;
    private final CategoryColorRegisterUseCase categoryColorRegisterUseCase;
    private final CategoryColorModifyUseCase categoryColorModifyUseCase;
    private final CategoryColorDeleteUseCase categoryColorDeleteUseCase;

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러 등록")
    @PostMapping("/category-colors")
    public CategoryColorResponse registerCategoryColor(
            @RequestBody @Valid CategoryColorRegisterRequest request) {
        CategoryColor categoryColor =
                categoryColorRegisterUseCase.execute(
                        new CategoryColorRegisterUseCase.Command(
                                request.colorCode(), request.colorName()));
        return categoryColorMapper.toCategoryColorResponse(categoryColor);
    }

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러 수정")
    @PutMapping("/category-colors/{categoryColorId}")
    public CategoryColorResponse modifyCategoryColor(
            @PathVariable Long categoryColorId,
            @RequestBody @Valid CategoryColorModifyRequest request) {
        CategoryColor categoryColor =
                categoryColorModifyUseCase.execute(
                        new CategoryColorModifyUseCase.Command(
                                categoryColorId, request.colorCode(), request.colorName()));
        return categoryColorMapper.toCategoryColorResponse(categoryColor);
    }

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러 삭제")
    @DeleteMapping("/category-colors/{categoryColorId}")
    public void deleteCategoryColor(@PathVariable Long categoryColorId) {
        categoryColorDeleteUseCase.delete(categoryColorId);
    }
}
