package com.todaysfail.api.web.category;

import com.todaysfail.api.web.category.dto.request.CategoryModifyRequest;
import com.todaysfail.api.web.category.dto.request.CategoryRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.category.usecase.CategoryDeleteUseCase;
import com.todaysfail.api.web.category.usecase.CategoryModifyUseCase;
import com.todaysfail.api.web.category.usecase.CategoryQueryUseCase;
import com.todaysfail.api.web.category.usecase.CategoryRegisterUseCase;
import com.todaysfail.domains.category.domain.CategoryColor;
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
    private final CategoryRegisterUseCase categoryRegisterUseCase;
    private final CategoryQueryUseCase categoryQueryUseCase;
    private final CategoryModifyUseCase categoryModifyUseCase;
    private final CategoryDeleteUseCase categoryDeleteUseCase;

    @Operation(summary = "카테고리 등록")
    @PostMapping
    public CategoryResponse registerCategory(
            @RequestBody @Valid final CategoryRegisterRequest request) {
        return categoryRegisterUseCase.execute(request);
    }

    @Operation(summary = "자신의 카테고리 조회")
    @GetMapping("/me")
    public List<CategoryResponse> myCategoryQueryAll() {
        return categoryQueryUseCase.execute();
    }

    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    public CategoryResponse modifyCategory(
            @PathVariable Long categoryId,
            @RequestBody @Valid final CategoryModifyRequest request) {
        return categoryModifyUseCase.execute(categoryId, request);
    }

    @Operation(summary = "카테고리 삭제")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable final Long categoryId) {
        categoryDeleteUseCase.execute(categoryId);
    }

    @Operation(summary = "카테고리 컬러 Enum 조회 [주의: Swagger 응답 예시와 다르니 꼭 호출해서 확인해 볼 것]")
    @GetMapping
    public List<CategoryColor> categoryColorQueryAll() {
        return Arrays.stream(CategoryColor.values()).collect(Collectors.toList());
    }
}
