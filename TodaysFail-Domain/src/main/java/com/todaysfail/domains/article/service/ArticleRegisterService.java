package com.todaysfail.domains.article.service;

import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleCommandPort;
import com.todaysfail.domains.article.usecase.ArticleRegisterUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleRegisterService implements ArticleRegisterUseCase {
    private final ArticleCommandPort articleCommandPort;

    @Override
    public Article execute(final Command command) {
        return articleCommandPort.save(
                Article.builder()
                        .title(command.title())
                        .content(command.content())
                        .thumbnail(command.thumbnail())
                        .link(command.link())
                        .build());
    }
}
