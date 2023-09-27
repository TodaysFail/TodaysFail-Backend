package com.todaysfail.api.web.failure.mapper;

import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.api.web.common.SliceResponse;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.tag.mapper.TagMapper;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.failure.domain.Failure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;

@Mapper
@RequiredArgsConstructor
public class FailureMapper {
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    public FailureResponse toFailureResponse(Failure failure) {
        Long userId = SecurityUtils.getCurrentUserId();
        boolean isMine = failure.getUser().getId().equals(userId);
        return new FailureResponse(
                failure.getId(),
                UserDetail.from(failure.getUser()),
                categoryMapper.toCategoryResponse(failure.getCategory()),
                failure.getFailureDate(),
                failure.getTitle(),
                failure.getContent(),
                failure.getImpression(),
                tagMapper.toTagResponseSet(failure.getTags()),
                failure.getHeartCount(),
                failure.isSecret(),
                isMine);
    }

    public SliceResponse<FailureResponse> toFailureSliceResponse(Slice<Failure> failureSlice) {
        return SliceResponse.of(failureSlice.map(failure -> toFailureResponse(failure)));
    }
}
