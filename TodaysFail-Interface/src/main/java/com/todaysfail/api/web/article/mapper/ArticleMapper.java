package com.todaysfail.api.web.article.mapper;

import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.article.domain.Article;
import org.springframework.data.domain.Slice;

@Mapper
public class ArticleMapper {
    public SliceResponse<ArticleResponse> toArticleSliceResponse(Slice<Article> articleSlice) {
        return SliceResponse.of(articleSlice.map(this::toArticleResponse));
    }

    public ArticleResponse toArticleResponse(Article article) {
        return ArticleResponse.of(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getThumbnail(),
                article.getLink(),
                article.getCreatedAt());
    }
}
