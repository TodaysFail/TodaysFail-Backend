package com.todaysfail.api.web.failure.mapper;

import com.todaysfail.api.web.category.mapper.CategoryMapper;
import com.todaysfail.api.web.failure.dto.response.FailureResponse;
import com.todaysfail.api.web.tag.mapper.TagMapper;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.failure.domain.Failure;
import com.todaysfail.domains.user.domain.UserDetail;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class FailureMapper {
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    public FailureResponse toFailureResponse(Failure failure) {
        return new FailureResponse(
                failure.getFailureId(),
                UserDetail.from(failure.getUser()),
                categoryMapper.toCategoryResponse(failure.getCategory()),
                failure.getFailureDate(),
                failure.getTitle(),
                failure.getContent(),
                failure.getImpression(),
                tagMapper.toTagResponseList(failure.getTagList()),
                failure.getHeartCount(),
                failure.isSecret());
    }
}
