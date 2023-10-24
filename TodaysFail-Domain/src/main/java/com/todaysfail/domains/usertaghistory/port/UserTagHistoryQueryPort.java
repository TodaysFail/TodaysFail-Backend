package com.todaysfail.domains.usertaghistory.port;

import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import java.util.List;

public interface UserTagHistoryQueryPort {
    List<UserTagHistory> queryUserTagHistory(Long userId);
}
