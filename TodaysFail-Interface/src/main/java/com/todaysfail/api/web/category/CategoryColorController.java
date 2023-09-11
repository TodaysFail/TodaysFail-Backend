package com.todaysfail.api.web.category;

import com.todaysfail.api.web.category.dto.request.CategoryColorRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.api.web.category.mapper.CategoryColorMapper;
import com.todaysfail.domains.category.domain.CategoryColor;
import com.todaysfail.domains.category.usecase.CategoryColorQueryUseCase;
import com.todaysfail.domains.category.usecase.CategoryColorRegisterUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "5-2. [카테고리 컬러]")
@RestController
@RequestMapping("/api/v1/category-colors")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class CategoryColorController {
    private final CategoryColorMapper categoryColorMapper;
    private final CategoryColorQueryUseCase categoryColorQueryUseCase;
    private final CategoryColorRegisterUseCase categoryColorRegisterUseCase;

    @GetMapping
    public List<CategoryColorResponse> categoryColorQueryAll() {
        List<CategoryColor> categoryColorList = categoryColorQueryUseCase.execute();
        return categoryColorMapper.toCategoryColorResponse(categoryColorList);
    }

    @PostMapping
    public CategoryColorResponse registerCategoryColor(
            @RequestBody @Valid CategoryColorRegisterRequest request) {
        CategoryColor categoryColor =
                categoryColorRegisterUseCase.execute(
                        new CategoryColorRegisterUseCase.Command(
                                request.colorCode(), request.colorName()));
        return categoryColorMapper.toCategoryColorResponse(categoryColor);
    }
}
