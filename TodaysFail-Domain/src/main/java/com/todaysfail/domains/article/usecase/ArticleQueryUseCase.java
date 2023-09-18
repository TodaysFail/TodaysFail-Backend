package com.todaysfail.domains.article.usecase;

import com.todaysfail.domains.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArticleQueryUseCase {
    Slice<Article> execute(final Query query);

    record Query(Pageable pageable) {}
}
