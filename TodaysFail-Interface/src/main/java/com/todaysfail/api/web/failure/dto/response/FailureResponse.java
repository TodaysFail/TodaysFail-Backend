package com.todaysfail.api.web.failure.dto.response;

import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.domains.user.domain.UserDetail;
import java.time.LocalDate;
import java.util.List;

public record FailureResponse(
        Long failureId,
        UserDetail user,
        CategoryResponse category,
        LocalDate failureDate,
        String title,
        String content,
        String impression,
        List<TagResponse> tagList,
        int heartCount,
        boolean secret) {}
