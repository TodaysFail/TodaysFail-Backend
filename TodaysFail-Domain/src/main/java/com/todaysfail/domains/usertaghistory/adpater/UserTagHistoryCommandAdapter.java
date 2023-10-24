package com.todaysfail.domains.usertaghistory.adpater;

import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import com.todaysfail.domains.usertaghistory.port.UserTagHistoryCommandPort;
import com.todaysfail.domains.usertaghistory.repository.UserTagHistoryRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class UserTagHistoryCommandAdapter implements UserTagHistoryCommandPort {
    private final UserTagHistoryRepository userTagHistoryRepository;

    @Override
    public UserTagHistory save(UserTagHistory userTagHistory) {
        return userTagHistoryRepository.save(userTagHistory);
    }
}
