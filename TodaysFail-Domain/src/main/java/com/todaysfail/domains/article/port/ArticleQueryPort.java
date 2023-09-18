package com.todaysfail.domains.article.port;

import com.todaysfail.domains.article.domain.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ArticleQueryPort {
    Slice<Article> queryArticle(Pageable pageable);
}
