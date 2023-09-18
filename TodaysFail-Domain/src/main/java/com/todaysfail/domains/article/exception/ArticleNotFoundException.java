package com.todaysfail.domains.article.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class ArticleNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new ArticleNotFoundException();

    public ArticleNotFoundException() {
        super(ArticleErrorCode.ARTICLE_NOT_FOUND);
    }
}
