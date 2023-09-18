package com.todaysfail.api.web.article.dto.request;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ArticleModifyRequest(
        @Size(max = 20) String title,
        String content,
        @Pattern(regexp = "^(http|https)://.*$", message = "http:// 또는 https://로 시작해야 합니다.")
                String thumbnail,
        @Pattern(regexp = "^(http|https)://.*$", message = "http:// 또는 https://로 시작해야 합니다.")
                String link) {}
