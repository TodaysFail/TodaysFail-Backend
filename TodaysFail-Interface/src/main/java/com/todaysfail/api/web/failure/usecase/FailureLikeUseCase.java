package com.todaysfail.api.web.failure.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.failure.service.FailureDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FailureLikeUseCase {
    private final FailureDomainService failureDomainService;

    public void execute(final Long failureId) {
        final Long userId = SecurityUtils.getCurrentUserId();
        failureDomainService.likeFailure(userId, failureId);
    }
}
