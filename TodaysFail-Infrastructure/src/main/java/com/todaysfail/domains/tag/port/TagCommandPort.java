package com.todaysfail.domains.tag.port;

import com.todaysfail.domains.tag.entity.TagEntity;
import java.util.Set;

public interface TagCommandPort {
    Set<TagEntity> saveAndRetrieveAllTags(Set<String> tagSet);
}
