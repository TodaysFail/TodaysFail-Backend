package com.todaysfail.api.web.failure;

import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.api.web.failure.dto.request.FailureModifyRequest;
import com.todaysfail.api.web.failure.dto.request.FailureRegisterRequest;
import com.todaysfail.api.web.failure.dto.response.FailureByCategoryResponse;
import com.todaysfail.api.web.failure.dto.response.FailureMonthlyDailyStatusResponse;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.failure.usecase.FailureByCategoryQueryUseCase;
import com.todaysfail.api.web.failure.usecase.FailureDeleteUseCase;
import com.todaysfail.api.web.failure.usecase.FailureFeedQueryUseCase;
import com.todaysfail.api.web.failure.usecase.FailureLikeUseCase;
import com.todaysfail.api.web.failure.usecase.FailureModifyUseCase;
import com.todaysfail.api.web.failure.usecase.FailureMonthlyDailyStatusUseCase;
import com.todaysfail.api.web.failure.usecase.FailureRegisterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.time.YearMonth;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "4. [실패]")
@RestController
@RequestMapping("/api/v1/failures")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class FailureController {
    private final FailureRegisterUseCase failureRegisterUseCase;
    private final FailureModifyUseCase failureModifyUseCase;
    private final FailureFeedQueryUseCase failureFeedQueryUseCase;
    private final FailureLikeUseCase failureLikeUseCase;
    private final FailureMonthlyDailyStatusUseCase failureMonthlyDailyStatusUseCase;
    private final FailureByCategoryQueryUseCase failureByCategoryQueryUseCase;
    private final FailureDeleteUseCase failureDeleteUseCase;

    @Operation(summary = "실패 등록")
    @PostMapping
    public FailureResponse registerFailure(@RequestBody @Valid FailureRegisterRequest request) {
        return failureRegisterUseCase.execute(request);
    }

    @Operation(summary = "실패 수정")
    @PutMapping("/{failureId}")
    public FailureResponse modifyFailure(
            @PathVariable Long failureId, @RequestBody FailureModifyRequest request) {
        return failureModifyUseCase.execute(failureId, request);
    }

    @Operation(summary = "실패 삭제")
    @DeleteMapping("/{failureId}")
    public void deleteFailure(@PathVariable Long failureId) {
        failureDeleteUseCase.execute(failureId);
    }

    @Operation(summary = "피드")
    @GetMapping("/feed")
    public SliceResponse<FailureResponse> queryFeed(
            @ParameterObject @PageableDefault final Pageable pageable) {
        return failureFeedQueryUseCase.execute(pageable);
    }

    @Operation(summary = "실패 좋아요")
    @PostMapping("/like/{failureId}")
    public void likeFailure(@PathVariable Long failureId) {
        failureLikeUseCase.execute(failureId);
    }

    @Operation(summary = "년, 월을 입력받아 일별 실패 기록여부 반환")
    @GetMapping("/monthly-daily-status")
    public FailureMonthlyDailyStatusResponse checkMonthFailures(@RequestParam YearMonth yearMonth) {
        return failureMonthlyDailyStatusUseCase.execute(yearMonth);
    }

    @Operation(summary = "선택 한 날짜의 실패 기록을 카테고리별로 조회")
    @GetMapping("/{date}")
    public FailureByCategoryResponse getFailures(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return failureByCategoryQueryUseCase.execute(date);
    }
}
