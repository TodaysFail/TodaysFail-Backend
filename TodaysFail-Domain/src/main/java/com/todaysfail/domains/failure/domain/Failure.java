package com.todaysfail.domains.failure.domain;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.failure.entity.FailureEntity;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Failure {
    private Long failureId;
    private User user;
    private Category category;
    private LocalDate failureDate;
    private String title;
    private String content;
    private String impression;
    private List<Tag> tagList;
    private int heartCount;

    public static Failure registerFailure(
            FailureEntity failureEntity, User user, Category category, List<Tag> tagList) {
        return new Failure(
                failureEntity.getId(),
                user,
                category,
                failureEntity.getFailureDate(),
                failureEntity.getTitle(),
                failureEntity.getContent(),
                failureEntity.getImpression(),
                tagList,
                failureEntity.getHeartCount());
    }
}
