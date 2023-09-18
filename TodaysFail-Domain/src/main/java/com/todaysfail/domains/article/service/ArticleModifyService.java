package com.todaysfail.domains.article.service;

import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleCommandPort;
import com.todaysfail.domains.article.usecase.ArticleModifyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleModifyService implements ArticleModifyUseCase {
    private final ArticleCommandPort articleCommandPort;

    @Override
    public Article execute(final Command command) {
        Article article = articleCommandPort.queryArticle(command.articleId());
        article.modify(command.title(), command.content(), command.thumbnail(), command.link());
        return article;
    }
}
