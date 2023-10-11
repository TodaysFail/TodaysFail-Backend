package com.todaysfail.domains.tag.port;

import com.todaysfail.domains.tag.domain.Tag;
import java.util.List;
import java.util.Set;

public interface TagQueryPort {
    List<Tag> queryAllByNames(Set<String> tags);

    List<Tag> queryAllByIds(List<Long> tags);
}
