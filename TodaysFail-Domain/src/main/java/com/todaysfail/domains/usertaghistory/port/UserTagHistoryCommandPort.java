package com.todaysfail.domains.usertaghistory.port;

import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;

public interface UserTagHistoryCommandPort {
    UserTagHistory save(UserTagHistory userTagHistory);
}
