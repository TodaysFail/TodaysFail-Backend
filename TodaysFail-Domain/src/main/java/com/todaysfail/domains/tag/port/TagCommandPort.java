package com.todaysfail.domains.tag.port;

import com.todaysfail.domains.tag.domain.Tag;
import java.util.List;
import java.util.Set;

public interface TagCommandPort {
    List<Tag> saveAndRetrieveAllTags(Set<String> tagSet);
}
