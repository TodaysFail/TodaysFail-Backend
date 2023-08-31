package com.todaysfail.domains.tag.port;

import com.todaysfail.domains.tag.entity.TagEntity;
import java.util.List;

public interface TagQueryPort {
    List<TagEntity> queryTagList(List<Long> tagIdList);
}
