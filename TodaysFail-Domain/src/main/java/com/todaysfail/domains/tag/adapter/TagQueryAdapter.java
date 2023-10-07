package com.todaysfail.domains.tag.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.exception.TagNotFoundException;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.domains.tag.repository.TagRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TagQueryAdapter implements TagQueryPort {
    private final TagRepository tagRepository;

    @Override
    public List<Tag> queryAllByNames(Set<String> tags) {
        return tags.stream()
                .map(
                        tagName ->
                                tagRepository
                                        .findByTagName(tagName)
                                        .orElseThrow(() -> TagNotFoundException.EXCEPTION))
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> queryAllByIds(List<Long> tags) {
        return tagRepository.findAllById(tags);
    }
}
