package com.todaysfail.domains.usertaghistory.repository;

import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagHistoryRepository extends JpaRepository<UserTagHistory, Long> {
    List<UserTagHistory> findTop5ByUserIdOrderByCreatedAtDesc(Long userId);
}
