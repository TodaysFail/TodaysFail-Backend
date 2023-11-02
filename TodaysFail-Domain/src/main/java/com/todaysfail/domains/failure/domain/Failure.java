package com.todaysfail.domains.failure.domain;

import com.todaysfail.common.BaseTimeEntity;
import com.todaysfail.config.converter.LongArrayConverter;
import com.todaysfail.domains.failure.exception.FailureNotOwnedByUserException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "tbl_failure")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Failure extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "failure_id")
    private Long id;

    private Long userId;

    private Long categoryId;

    private LocalDate failureDate;

    private String title;

    private String content;

    private String impression;

    @Convert(converter = LongArrayConverter.class)
    private List<Long> tags = new ArrayList<>();

    private int heartCount;

    private boolean secret;

    public boolean isMine(Long userId) {
        return this.userId.equals(userId);
    }

    public void like() {
        this.heartCount++;
    }

    public void validateOwnership(Long userId) {
        if (!this.userId.equals(userId)) {
            throw FailureNotOwnedByUserException.EXCEPTION;
        }
    }

    public void modify(Failure failure) {
        this.categoryId = failure.getCategoryId();
        this.failureDate = failure.getFailureDate();
        this.title = failure.getTitle();
        this.content = failure.getContent();
        this.tags = failure.getTags();
        this.secret = failure.isSecret();
    }
}
