package com.todaysfail.domains.article.port;

import com.todaysfail.domains.article.domain.Article;

public interface ArticleCommandPort {
    Article save(final Article article);

    Article queryArticle(final Long articleId);

    void delete(final Article article);
}
