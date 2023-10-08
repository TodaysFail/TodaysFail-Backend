package com.todaysfail.api.web.article.usecase;

import com.todaysfail.api.web.article.dto.request.ArticleRegisterRequest;
import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.mapper.ArticleMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.service.ArticleDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ArticleRegisterUseCase {
    public static ArticleMapper articleMapper;
    public static ArticleDomainService articleDomainService;

    public ArticleResponse execute(final ArticleRegisterRequest request) {
        Article article =
                Article.builder()
                        .title(request.title())
                        .content(request.content())
                        .thumbnail(request.thumbnail())
                        .link(request.link())
                        .build();
        Article savedArticle = articleDomainService.register(article);
        return articleMapper.toArticleResponse(savedArticle);
    }
}
