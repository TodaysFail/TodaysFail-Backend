package com.todaysfail.api.web.article.dto.response;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long articleId,
        String title,
        String content,
        String thumbnail,
        String link,
        LocalDateTime createdAt) {
    public static ArticleResponse of(
            Long articleId,
            String title,
            String content,
            String thumbnail,
            String link,
            LocalDateTime createdAt) {
        return new ArticleResponse(articleId, title, content, thumbnail, link, createdAt);
    }
}
