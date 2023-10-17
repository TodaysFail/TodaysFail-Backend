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
import com.todaysfail.domains.tag.service.TagDomainService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class FailureRegisterUseCase {
    private final FailureMapper failureMapper;
    private final CategoryQueryPort categoryQueryPort;
    private final FailureDomainService failureDomainService;
    private final TagDomainService tagDomainService;

    @Transactional
    public FailureResponse execute(FailureRegisterRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        List<Tag> tags = tagDomainService.saveAndRetrieveAllTags(request.tagNameSet());
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
        Failure registeredFailure = failureDomainService.register(failure, category, tags);
        return failureMapper.toFailureResponse(registeredFailure);
    }
}
