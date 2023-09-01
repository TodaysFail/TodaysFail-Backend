package com.todaysfail.domains.failure.entity;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_failure")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class FailureEntity {
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "tbl_failure_tag", joinColumns = @JoinColumn(name = "failure_id"))
    private Set<String> tags;

    private int heartCount;

    private boolean secret;

    public static FailureEntity registerFailure(
            Long useId,
            Long categoryId,
            LocalDate date,
            String title,
            String content,
            String impression,
            Set<String> tagSet) {
        return new FailureEntity(
                null, useId, categoryId, date, title, content, impression, tagSet, 0, false);
    }
}
