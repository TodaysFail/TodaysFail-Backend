package com.todaysfail.api.web.failure.dto.request;

import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

public record FailureRegisterRequest(
        @NotNull Long categoryId,
        @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
        @NotNull @Size(min = 1, max = 50) String title,
        @NotNull @Size(min = 1, max = 500) String content,
        @NotNull @Size(min = 1, max = 500) String impression,
        @NotNull @Size(min = 1, max = 3) List<Long> tagIdList) {}
