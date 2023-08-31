package com.todaysfail.domains.tag.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.tag.entity.TagEntity;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.domains.tag.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagQueryAdapter implements TagQueryPort {
    private final TagRepository tagRepository;

    @Override
    public List<TagEntity> queryTagList(List<Long> tagIdList) {
        return tagRepository.findAllById(tagIdList);
    }
}
