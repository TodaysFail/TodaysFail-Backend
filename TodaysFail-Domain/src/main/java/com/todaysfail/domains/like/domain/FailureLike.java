package com.todaysfail.domains.like.domain;

import com.todaysfail.common.BaseTimeEntity;
import javax.persistence.Column;
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
@Entity(name = "tbl_failure_like")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FailureLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "failure_like_id")
    private Long id;

    private Long userId;

    private Long failureId;

    public static FailureLike of(Long userId, Long failureId) {
        return FailureLike.builder().userId(userId).failureId(failureId).build();
    }
}
