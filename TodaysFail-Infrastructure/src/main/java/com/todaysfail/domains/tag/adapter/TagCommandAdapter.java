package com.todaysfail.domains.tag.adapter;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.tag.entity.TagEntity;
import com.todaysfail.domains.tag.port.TagCommandPort;
import com.todaysfail.domains.tag.repository.TagRepository;
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
    public Set<TagEntity> saveAndRetrieveAllTags(Set<String> tagSet) {
        return tagSet.stream()
                .map(
                        tagName ->
                                tagRepository
                                        .findByTagName(tagName)
                                        .orElseGet(
                                                () -> {
                                                    TagEntity newTagEntity =
                                                            tagRepository.save(
                                                                    tagRepository.save(
                                                                            TagEntity.registerTag(
                                                                                    tagName)));
                                                    return newTagEntity;
                                                }))
                .collect(Collectors.toSet());
    }
}
