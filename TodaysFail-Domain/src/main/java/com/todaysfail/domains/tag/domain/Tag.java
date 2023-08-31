package com.todaysfail.domains.tag.domain;

import com.todaysfail.domains.tag.entity.TagEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Tag {
    private Long tagId;
    private String tagName;

    public static Tag from(TagEntity tagEntity) {
        return new Tag(tagEntity.getId(), tagEntity.getTagName());
    }
}
