package com.todaysfail.domains.usertaghistory.adpater;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import com.todaysfail.domains.usertaghistory.port.UserTagHistoryQueryPort;
import com.todaysfail.domains.usertaghistory.repository.UserTagHistoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class UserTagHistoryQueryAdapter implements UserTagHistoryQueryPort {
    private final UserTagHistoryRepository userTagHistoryRepository;

    @Override
    public List<UserTagHistory> queryUserTagHistory(Long userId) {
        return userTagHistoryRepository.findTop5ByUserIdOrderByCreatedAtDesc(userId);
    }
}
