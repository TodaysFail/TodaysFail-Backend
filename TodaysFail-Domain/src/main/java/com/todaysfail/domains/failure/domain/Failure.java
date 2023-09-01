package com.todaysfail.domains.failure.domain;

import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.failure.entity.FailureEntity;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.user.domain.User;
import java.time.LocalDate;
import java.util.Set;
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
    private Set<Tag> tagSet;
    private int heartCount;
    private boolean secret;

    public static Failure registerFailure(
            FailureEntity failureEntity, User user, Category category, Set<Tag> tagSet) {
        return new Failure(
                failureEntity.getId(),
                user,
                category,
                failureEntity.getFailureDate(),
                failureEntity.getTitle(),
                failureEntity.getContent(),
                failureEntity.getImpression(),
                tagSet,
                failureEntity.getHeartCount(),
                false);
    }
}
