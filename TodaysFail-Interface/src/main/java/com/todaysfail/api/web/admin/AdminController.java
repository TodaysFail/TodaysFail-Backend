package com.todaysfail.api.web.admin;

import com.todaysfail.api.web.article.dto.request.ArticleRegisterRequest;
import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.mapper.ArticleMapper;
import com.todaysfail.api.web.category.dto.request.CategoryColorModifyRequest;
import com.todaysfail.api.web.category.dto.request.CategoryColorRegisterRequest;
import com.todaysfail.api.web.category.dto.response.CategoryColorResponse;
import com.todaysfail.api.web.category.mapper.CategoryColorMapper;
import com.todaysfail.domains.article.usecase.ArticleDeleteUseCase;
import com.todaysfail.domains.article.usecase.ArticleModifyUseCase;
import com.todaysfail.domains.article.usecase.ArticleRegisterUseCase;
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
    private final ArticleMapper articleMapper;
    private final CategoryColorRegisterUseCase categoryColorRegisterUseCase;
    private final CategoryColorModifyUseCase categoryColorModifyUseCase;
    private final CategoryColorDeleteUseCase categoryColorDeleteUseCase;
    private final ArticleRegisterUseCase articleRegisterUseCase;
    private final ArticleModifyUseCase articleModifyUseCase;
    private final ArticleDeleteUseCase articleDeleteUseCase;

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러를 등록합니다.")
    @PostMapping("/category-colors")
    public CategoryColorResponse registerCategoryColor(
            @RequestBody @Valid final CategoryColorRegisterRequest request) {
        CategoryColor categoryColor =
                categoryColorRegisterUseCase.execute(
                        new CategoryColorRegisterUseCase.Command(
                                request.colorCode(), request.colorName()));
        return categoryColorMapper.toCategoryColorResponse(categoryColor);
    }

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러를 수정합니다.")
    @PutMapping("/category-colors/{categoryColorId}")
    public CategoryColorResponse modifyCategoryColor(
            @PathVariable final Long categoryColorId,
            @RequestBody @Valid final CategoryColorModifyRequest request) {
        CategoryColor categoryColor =
                categoryColorModifyUseCase.execute(
                        new CategoryColorModifyUseCase.Command(
                                categoryColorId, request.colorCode(), request.colorName()));
        return categoryColorMapper.toCategoryColorResponse(categoryColor);
    }

    @Tag(name = "Z-1. [관리자 - 카테고리]")
    @Operation(summary = "카테고리 컬러를 삭제합니다.")
    @DeleteMapping("/category-colors/{categoryColorId}")
    public void deleteCategoryColor(@PathVariable final Long categoryColorId) {
        categoryColorDeleteUseCase.delete(categoryColorId);
    }

    @Tag(name = "Z-2. [관리자 - 아티클]")
    @Operation(summary = "아티클을 저장합니다.")
    @PostMapping("/articles")
    public ArticleResponse registerArticle(
            @RequestBody @Valid final ArticleRegisterRequest request) {
        return articleMapper.toArticleResponse(
                articleRegisterUseCase.execute(
                        new ArticleRegisterUseCase.Command(
                                request.title(),
                                request.content(),
                                request.thumbnail(),
                                request.link())));
    }

    @Tag(name = "Z-2. [관리자 - 아티클]")
    @Operation(summary = "아티클을 수정합니다.")
    @PutMapping("/articles/{articleId}")
    public ArticleResponse modifyArticle(
            @PathVariable final Long articleId,
            @RequestBody @Valid final ArticleRegisterRequest request) {
        return articleMapper.toArticleResponse(
                articleModifyUseCase.execute(
                        new ArticleModifyUseCase.Command(
                                articleId,
                                request.title(),
                                request.content(),
                                request.thumbnail(),
                                request.link())));
    }

    @Tag(name = "Z-2. [관리자 - 아티클]")
    @Operation(summary = "아티클을 삭제합니다.")
    @DeleteMapping("/articles/{articleId}")
    public void deleteArticle(@PathVariable final Long articleId) {
        articleDeleteUseCase.execute(new ArticleDeleteUseCase.Command(articleId));
    }
}
