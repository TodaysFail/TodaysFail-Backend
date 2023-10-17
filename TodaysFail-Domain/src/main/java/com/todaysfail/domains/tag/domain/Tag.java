package com.todaysfail.domains.tag.domain;

import com.todaysfail.common.BaseTimeEntity;
import com.todaysfail.domains.tag.exception.TagNameLengthExceedException;
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
@Entity(name = "tbl_tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tag extends BaseTimeEntity {
    private static final int MAX_TAG_NAME_LENGTH = 23;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String tagName;

    private Long usedCount;

    public static Tag register(String tagName) {
        return Tag.builder().tagName(tagName).usedCount(0L).build();
    }

    public void increaseUsedCount() {
        this.usedCount++;
    }

    public void validateTagNameLength() {
        if (this.tagName.length() > MAX_TAG_NAME_LENGTH) {
            throw TagNameLengthExceedException.EXCEPTION;
        }
    }
}
