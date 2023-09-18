package com.todaysfail.domains.article.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.exception.ArticleNotFoundException;
import com.todaysfail.domains.article.port.ArticleCommandPort;
import com.todaysfail.domains.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class ArticleCommandAdapter implements ArticleCommandPort {
    private final ArticleRepository articleRepository;

    @Override
    public Article save(final Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article queryArticle(final Long articleId) {
        return articleRepository
                .findById(articleId)
                .orElseThrow(() -> ArticleNotFoundException.EXCEPTION);
    }

    @Override
    public void delete(final Article article) {
        articleRepository.delete(article);
    }
}
