package com.todaysfail.domains.failure.usecase;

import com.todaysfail.domains.failure.domain.Failure;
import java.time.LocalDate;
import java.util.Set;

public interface FailureRegisterUseCase {
    Failure execute(Command command);

    record Command(
            Long userId,
            Long categoryId,
            LocalDate date,
            String title,
            String content,
            String impression,
            Set<String> tagSet,
            boolean secret) {}
}
