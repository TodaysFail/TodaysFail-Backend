package com.todaysfail.domains.failure.port;

import com.todaysfail.domains.failure.entity.FailureEntity;
import java.time.LocalDate;
import java.util.List;

public interface FailureCommandPort {
    FailureEntity registerFailure(
            Long useId,
            Long categoryId,
            LocalDate date,
            String title,
            String content,
            String impression,
            List<Long> tagIdList);
}
