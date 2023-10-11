package com.todaysfail.api.web.failure.usecase;

import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.failure.mapper.FailureMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

@UseCase
@RequiredArgsConstructor
public class FailureFeedQueryUseCase {
    private final FailureMapper failureMapper;
    private final FailureQueryPort failureQueryPort;

    public SliceResponse<FailureResponse> execute(Pageable pageable) {
        Slice<Failure> failures = failureQueryPort.queryFeed(pageable);
        return failureMapper.toFailureSliceResponse(failures);
    }
}
