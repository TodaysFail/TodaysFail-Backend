package com.todaysfail.api.web.failure.dto.response;

import com.todaysfail.api.web.category.dto.response.CategoryResponse;
import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.user.domain.User;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record FailureResponse(
        Long failureId,
        UserDetail user,
        CategoryResponse category,
        LocalDate failureDate,
        String title,
        String content,
        String impression,
        Set<TagResponse> tagList,
        int heartCount,
        boolean secret,
        boolean isMine,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
    public static FailureResponse of(
            Failure failure, User user, Category category, List<Tag> tags, boolean isMine) {
        return new FailureResponse(
                failure.getId(),
                UserDetail.from(user),
                CategoryResponse.from(category),
                failure.getFailureDate(),
                failure.getTitle(),
                failure.getContent(),
                failure.getImpression(),
                tags.stream().map(TagResponse::from).collect(Collectors.toSet()),
                failure.getHeartCount(),
                failure.isSecret(),
                isMine,
                failure.getCreatedAt(),
                failure.getUpdatedAt());
    }
}
