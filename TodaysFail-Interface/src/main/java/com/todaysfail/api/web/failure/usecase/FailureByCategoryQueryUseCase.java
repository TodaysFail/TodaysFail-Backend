package com.todaysfail.api.web.failure.usecase;

import com.todaysfail.api.web.failure.dto.response.FailureByCategoryResponse;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.failure.mapper.FailureMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FailureByCategoryQueryUseCase {
    private final FailureMapper failureMapper;
    private final FailureQueryPort failureQueryPort;

    public FailureByCategoryResponse execute(final LocalDate date) {
        final Long userId = SecurityUtils.getCurrentUserId();
        List<Failure> failures = failureQueryPort.queryFailureByUserIdAndDate(userId, date);
        Map<Long, List<FailureResponse>> map =
                failureMapper.toFailureListResponse(failures).stream()
                        .collect(
                                Collectors.groupingBy(
                                        failureResponse ->
                                                failureResponse.category().categoryId()));
        return new FailureByCategoryResponse(map);
    }
}
