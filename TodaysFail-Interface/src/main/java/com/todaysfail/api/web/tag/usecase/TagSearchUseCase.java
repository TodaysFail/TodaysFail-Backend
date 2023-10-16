package com.todaysfail.api.web.tag.usecase;

import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.api.web.tag.mapper.TagMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagQueryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class TagSearchUseCase {
    private final TagMapper tagMapper;
    private final TagQueryPort tagQueryPort;

    public List<TagResponse> execute(String searchKeyword) {
        List<Tag> tagList = tagQueryPort.querySearch(searchKeyword);
        return tagMapper.toTagResponseList(tagList);
    }
}
