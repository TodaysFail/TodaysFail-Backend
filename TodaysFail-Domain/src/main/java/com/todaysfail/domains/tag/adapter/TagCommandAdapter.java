package com.todaysfail.domains.tag.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagCommandPort;
import com.todaysfail.domains.tag.repository.TagRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Adapter
@Transactional
@RequiredArgsConstructor
public class TagCommandAdapter implements TagCommandPort {
    private final TagRepository tagRepository;

    @Override
    public List<Tag> saveAndRetrieveAllTags(Set<String> tagSet) {
        return tagSet.stream()
                .map(
                        tagName ->
                                tagRepository
                                        .findByTagName(tagName)
                                        .orElseGet(
                                                () ->
                                                        tagRepository.save(
                                                                Tag.builder()
                                                                        .tagName(tagName)
                                                                        .build())))
                .collect(Collectors.toList());
    }
}
