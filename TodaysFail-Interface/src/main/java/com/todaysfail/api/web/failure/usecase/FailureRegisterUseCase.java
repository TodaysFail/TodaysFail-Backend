package com.todaysfail.api.web.failure.usecase;

import com.todaysfail.api.web.failure.dto.request.FailureRegisterRequest;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.failure.mapper.FailureMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.failure.service.FailureDomainService;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagCommandPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FailureRegisterUseCase {
    private final FailureMapper failureMapper;
    private final TagCommandPort tagCommandPort;
    private final CategoryQueryPort categoryQueryPort;
    private final FailureDomainService failureDomainService;

    public FailureResponse execute(FailureRegisterRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        List<Tag> tags = tagCommandPort.saveAndRetrieveAllTags(request.tagNameSet());
        Category category = categoryQueryPort.queryCategory(request.categoryId());
        Failure failure =
                Failure.builder()
                        .userId(currentUserId)
                        .categoryId(request.categoryId())
                        .failureDate(request.date())
                        .title(request.title())
                        .content(request.content())
                        .impression(request.impression())
                        .tags(tags.stream().map(Tag::getId).collect(Collectors.toList()))
                        .secret(request.secret())
                        .build();
        Failure registeredFailure = failureDomainService.register(failure, category);
        return failureMapper.toFailureResponse(registeredFailure);
    }
}
