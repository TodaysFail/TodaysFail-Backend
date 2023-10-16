package com.todaysfail.domains.tag.service;

import com.todaysfail.aop.lock.RedissonLock;
import com.todaysfail.domains.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagDomainService {

    @RedissonLock(lockName = "태그사용수증가", identifier = "tag")
    public Tag increaseUsedCount(Tag tag) {
        tag.increaseUsedCount();
        return tag;
    }
}
