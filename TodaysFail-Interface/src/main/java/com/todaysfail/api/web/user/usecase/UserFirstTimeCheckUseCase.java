package com.todaysfail.api.web.user.usecase;

import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.port.FailureQueryPort;
import java.util.List;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UserFirstTimeCheckUseCase {
    private final CategoryQueryPort categoryQueryPort;
    private final FailureQueryPort failureQueryPort;

    public boolean execute() {
        final Long userId = SecurityUtils.getCurrentUserId();
        List<Category> categories = categoryQueryPort.queryCategoryByUserId(userId);
        if (!categories.isEmpty()) {
            return false;
        }

        List<Failure> failures = failureQueryPort.queryFailureByUserId(userId);
        if (!failures.isEmpty()) {
            return false;
        }

        return true;
    }
}
