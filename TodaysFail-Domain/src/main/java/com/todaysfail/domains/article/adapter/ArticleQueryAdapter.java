package com.todaysfail.domains.article.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleQueryPort;
import com.todaysfail.domains.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleQueryAdapter implements ArticleQueryPort {
    private final ArticleRepository articleRepository;

    @Override
    public Slice<Article> queryArticle(final Pageable pageable) {
        return articleRepository.findAll(pageable);
    }
}
