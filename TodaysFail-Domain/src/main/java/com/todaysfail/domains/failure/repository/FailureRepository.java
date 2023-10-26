package com.todaysfail.domains.failure.repository;

import com.todaysfail.domains.failure.domain.Failure;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FailureRepository extends JpaRepository<Failure, Long> {
    Slice<Failure> findAllBySecretFalseOrderByFailureDateDesc(Pageable pageable);

    List<Failure> findAllByUserId(Long userId);

    @Query(
            "select DAY(f.failureDate) from tbl_failure f "
                    + "where YEAR(f.failureDate) = :year "
                    + "and month(f.failureDate) = :month "
                    + "group by DAY(f.failureDate)")
    List<Integer> findDailyStatusByYearMonth(int year, int month);

    List<Failure> findAllByUserIdAndFailureDate(Long userId, LocalDate date);
}
