package com.todaysfail.domains.article.service;

import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleCommandPort;
import com.todaysfail.domains.article.usecase.ArticleDeleteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleDeleteService implements ArticleDeleteUseCase {
    private final ArticleCommandPort articleCommandPort;

    @Override
    @Transactional
    public void execute(final Command command) {
        Article article = articleCommandPort.queryArticle(command.articleId());
        articleCommandPort.delete(article);
    }
}
