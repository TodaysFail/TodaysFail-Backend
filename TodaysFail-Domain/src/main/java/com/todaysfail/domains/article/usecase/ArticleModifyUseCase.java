package com.todaysfail.domains.article.usecase;

import com.todaysfail.domains.article.domain.Article;

public interface ArticleModifyUseCase {
    Article execute(final Command command);

    record Command(Long articleId, String title, String content, String thumbnail, String link) {}
}
