package com.todaysfail.domains.article.usecase;

import com.todaysfail.domains.article.domain.Article;

public interface ArticleRegisterUseCase {
    Article execute(final Command command);

    record Command(String title, String content, String thumbnail, String link) {}
}
