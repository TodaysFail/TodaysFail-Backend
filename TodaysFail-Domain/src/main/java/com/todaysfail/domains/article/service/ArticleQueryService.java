package com.todaysfail.domains.article.service;

import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleQueryPort;
import com.todaysfail.domains.article.usecase.ArticleQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleQueryService implements ArticleQueryUseCase {
    private final ArticleQueryPort articleQueryPort;

    @Override
    public Slice<Article> execute(final Query query) {
        return articleQueryPort.queryArticle(query.pageable());
    }
}
