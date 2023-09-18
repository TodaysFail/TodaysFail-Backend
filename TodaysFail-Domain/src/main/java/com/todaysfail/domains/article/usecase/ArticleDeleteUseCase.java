package com.todaysfail.domains.article.usecase;

public interface ArticleDeleteUseCase {
    void execute(final Command command);

    record Command(Long articleId) {}
}
