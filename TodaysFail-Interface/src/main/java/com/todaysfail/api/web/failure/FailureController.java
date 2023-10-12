package com.todaysfail.api.web.failure;

import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.api.web.failure.dto.request.FailureRegisterRequest;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.failure.usecase.FailureFeedQueryUseCase;
import com.todaysfail.api.web.failure.usecase.FailureLikeUseCase;
import com.todaysfail.api.web.failure.usecase.FailureRegisterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "4. [실패]")
@RestController
@RequestMapping("/api/v1/failures")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class FailureController {
    private final FailureRegisterUseCase failureRegisterUseCase;
    private final FailureFeedQueryUseCase failureFeedQueryUseCase;
    private final FailureLikeUseCase failureLikeUseCase;

    @Operation(summary = "실패 등록")
    @PostMapping
    public FailureResponse registerFailure(@RequestBody @Valid FailureRegisterRequest request) {
        return failureRegisterUseCase.execute(request);
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
}
