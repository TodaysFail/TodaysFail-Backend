package com.todaysfail.api.web.failure.mapper;

import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.category.domain.Category;
import com.todaysfail.domains.category.port.CategoryQueryPort;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.tag.domain.Tag;
import com.todaysfail.domains.tag.port.TagQueryPort;
import com.todaysfail.domains.user.domain.User;
import com.todaysfail.domains.user.port.UserQueryPort;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;

@Mapper
@RequiredArgsConstructor
public class FailureMapper {
    private final CategoryQueryPort categoryQueryPort;
    private final UserQueryPort userQueryPort;
    private final TagQueryPort tagQueryPort;

    public FailureResponse toFailureResponse(Failure failure) {
        Long userId = SecurityUtils.getCurrentUserId();
        User user = userQueryPort.queryUser(failure.getUserId());
        Category category = categoryQueryPort.queryCategory(failure.getCategoryId());
        List<Tag> tags = tagQueryPort.queryAllByIds(failure.getTags());
        boolean isMine = failure.isMine(userId);
        return FailureResponse.of(failure, user, category, tags, isMine);
    }

    public SliceResponse<FailureResponse> toFailureSliceResponse(Slice<Failure> failureSlice) {
        return SliceResponse.of(failureSlice.map(failure -> toFailureResponse(failure)));
    }

    public List<FailureResponse> toFailureListResponse(List<Failure> failures) {
        return failures.stream()
                .map(failure -> toFailureResponse(failure))
                .collect(Collectors.toList());
    }
}
