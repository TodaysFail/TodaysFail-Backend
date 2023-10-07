package com.todaysfail.api.web.admin;

import com.todaysfail.api.web.article.dto.request.ArticleRegisterRequest;
import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.mapper.ArticleMapper;
import com.todaysfail.domains.article.usecase.ArticleDeleteUseCase;
import com.todaysfail.domains.article.usecase.ArticleModifyUseCase;
import com.todaysfail.domains.article.usecase.ArticleRegisterUseCase;
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
    private final ArticleMapper articleMapper;
    private final ArticleRegisterUseCase articleRegisterUseCase;
    private final ArticleModifyUseCase articleModifyUseCase;
    private final ArticleDeleteUseCase articleDeleteUseCase;

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
