package com.todaysfail.api.web.article;

import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.usecase.ArticleQueryUseCase;
import com.todaysfail.api.web.common.SliceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "6. [아티클]")
@RestController
@RequestMapping("/api/v1/articles")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleQueryUseCase articleQueryUseCase;

    @Operation(summary = "아티클을 조회합니다.")
    @GetMapping
    public SliceResponse<ArticleResponse> queryArticle(
            @ParameterObject @PageableDefault final Pageable pageable) {
        return articleQueryUseCase.execute(pageable);
    }
}
