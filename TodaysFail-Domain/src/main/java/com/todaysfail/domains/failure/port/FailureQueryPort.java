package com.todaysfail.domains.failure.port;

import com.todaysfail.domains.failure.domain.Failure;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface FailureQueryPort {
    Slice<Failure> queryFeed(Pageable pageable);

    List<Failure> queryFailureByUserId(Long userId);

    List<Integer> queryDailyStatusByYearMonth(int year, int month);

    List<Failure> queryFailureByUserIdAndDate(Long userId, LocalDate date);
}
