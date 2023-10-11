package com.todaysfail.api.web.tag.dto.response;

import com.todaysfail.domains.tag.domain.Tag;

public record TagResponse(Long tagId, String tagName) {
    public static TagResponse from(Tag tag) {
        return new TagResponse(tag.getId(), tag.getTagName());
    }
}
