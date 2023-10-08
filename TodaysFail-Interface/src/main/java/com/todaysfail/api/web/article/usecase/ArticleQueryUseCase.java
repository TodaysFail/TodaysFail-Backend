package com.todaysfail.api.web.article.usecase;

import com.todaysfail.api.web.article.dto.response.ArticleResponse;
import com.todaysfail.api.web.article.mapper.ArticleMapper;
import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.article.domain.Article;
import com.todaysfail.domains.article.port.ArticleQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@UseCase
@RequiredArgsConstructor
public class ArticleQueryUseCase {
    private final ArticleMapper articleMapper;
    private final ArticleQueryPort articleQueryPort;

    public SliceResponse<ArticleResponse> execute(Pageable pageable) {
        Slice<Article> articles = articleQueryPort.queryArticle(pageable);
        return articleMapper.toArticleSliceResponse(articles);
    }
    // Slice<Article> execute(final Query query);
    //
    // record Query(Pageable pageable) {}
}
