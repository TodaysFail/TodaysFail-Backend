package com.todaysfail.api.web.article.usecase;

import com.todaysfail.api.web.article.dto.request.ArticleModifyRequest;
import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.mapper.ArticleMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.service.ArticleDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ArticleModifyUseCase {
    private final ArticleMapper articleMapper;
    private final ArticleDomainService articleDomainService;

    public ArticleResponse execute(final Long articleId, final ArticleModifyRequest request) {
        Article article =
                articleDomainService.modify(
                        articleId,
                        request.title(),
                        request.content(),
                        request.thumbnail(),
                        request.link());
        return articleMapper.toArticleResponse(article);
    }
}
