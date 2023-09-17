package com.todaysfail.api.web.tag.mapper;

import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.tag.domain.Tag;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public class TagMapper {
    public TagResponse toTagResponse(Tag tag) {
        return new TagResponse(tag.getId(), tag.getTagName());
    }

    public Set<TagResponse> toTagResponseSet(Set<Tag> tagSet) {
        return tagSet.stream().map(this::toTagResponse).collect(Collectors.toSet());
    }
}
