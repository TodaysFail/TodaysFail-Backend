package com.todaysfail.api.web.tag.usecase;

import com.todaysfail.api.web.tag.dto.response.TagResponse;
import com.todaysfail.api.web.tag.mapper.TagMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import com.todaysfail.domains.usertaghistory.port.UserTagHistoryQueryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserTagHistoryQueryUseCase {
    private final TagMapper tagMapper;
    private final UserTagHistoryQueryPort userTagHistoryQueryPort;
    private final TagQueryPort tagQueryPort;

    public List<TagResponse> execute() {
        final Long userId = SecurityUtils.getCurrentUserId();
        List<UserTagHistory> userTagHistories = userTagHistoryQueryPort.queryUserTagHistory(userId);
        List<Tag> tags =
                tagQueryPort.queryAllByIds(
                        userTagHistories.stream().map(UserTagHistory::getTagId).toList());
        return tagMapper.toTagResponseList(tags);
    }
}
