package com.todaysfail.api.web.failure.usecase;

import com.todaysfail.api.web.failure.dto.response.FailureMonthlyDailyStatusResponse;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import java.time.YearMonth;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FailureMonthlyDailyStatusUseCase {
    private final FailureQueryPort failureQueryPort;

    public FailureMonthlyDailyStatusResponse execute(YearMonth yearMonth) {
        List<Integer> dailyStatus =
                failureQueryPort.queryDailyStatusByYearMonth(
                        yearMonth.getYear(), yearMonth.getMonthValue());
        return new FailureMonthlyDailyStatusResponse(dailyStatus);
    }
}
