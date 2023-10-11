package com.todaysfail.api.web.article.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.article.service.ArticleDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ArticleDeleteUseCase {
    private final ArticleDomainService articleDomainService;

    public void execute(final Long articleId) {
        articleDomainService.delete(articleId);
    }
}
