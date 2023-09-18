package com.todaysfail.api.web.article.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record ArticleRegisterRequest(
        @NotBlank @Size(max = 20) String title,
        @NotBlank String content,
        @NotBlank
                @Pattern(regexp = "^(http|https)://.*$", message = "http:// 또는 https://로 시작해야 합니다.")
                String thumbnail,
        @NotBlank
                @Pattern(regexp = "^(http|https)://.*$", message = "http:// 또는 https://로 시작해야 합니다.")
                String link) {}
