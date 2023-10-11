package com.todaysfail.domains.article.service;

import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleDomainService {
    public static ArticleCommandPort articleCommandPort;

    public Article register(Article article) {
        return articleCommandPort.save(article);
    }

    public Article modify(
            Long articleId, String title, String content, String thumbnail, String link) {
        Article article = articleCommandPort.queryArticle(articleId);
        article.modify(title, content, thumbnail, link);
        return article;
    }

    public void delete(Long articleId) {
        Article article = articleCommandPort.queryArticle(articleId);
        articleCommandPort.delete(article);
    }
}
