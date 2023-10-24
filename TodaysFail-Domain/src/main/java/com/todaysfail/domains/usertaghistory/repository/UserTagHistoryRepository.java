package com.todaysfail.domains.usertaghistory.repository;

import com.todaysfail.domains.usertaghistory.domain.UserTagHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagHistoryRepository extends JpaRepository<UserTagHistory, Long> {}
