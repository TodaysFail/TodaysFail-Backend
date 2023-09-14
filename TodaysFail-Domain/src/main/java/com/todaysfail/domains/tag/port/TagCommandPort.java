package com.todaysfail.domains.tag.port;

import com.todaysfail.domains.tag.domain.Tag;
import java.util.Set;

public interface TagCommandPort {
    Set<Tag> saveAndRetrieveAllTags(Set<String> tagSet);
}
