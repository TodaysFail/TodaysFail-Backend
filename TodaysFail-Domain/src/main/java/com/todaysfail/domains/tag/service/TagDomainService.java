package com.todaysfail.domains.tag.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagCommandPort;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagDomainService {
    private final TagCommandPort tagCommandPort;

    @RedissonLock(lockName = "태그사용수증가", identifier = "tag")
    public Tag increaseUsedCount(Tag tag) {
        tag.increaseUsedCount();
        return tag;
    }

    @Transactional
    public List<Tag> saveAndRetrieveAllTags(Set<String> tagNameSet) {
        List<Tag> tags = tagCommandPort.saveAndRetrieveAllTags(tagNameSet);
        tags.stream().forEach(tag -> tag.validateTagNameLength());
        return tags;
    }
}
