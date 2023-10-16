package com.todaysfail.api.web.tag.mapper;

import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.tag.domain.Tag;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class TagMapper {
    public TagResponse toTagResponse(Tag tag) {
        return TagResponse.from(tag);
    }

    public List<TagResponse> toTagResponseList(List<Tag> tagList) {
        return tagList.stream().map(this::toTagResponse).collect(Collectors.toList());
    }
}
