package com.todaysfail.api.web.image.usecase;

import com.todaysfail.api.web.image.dto.response.PresignedUrlResponse;
import com.todaysfail.api.web.image.mapper.ImageMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.image.domain.PresignedUrl;
import com.todaysfail.domains.image.service.ImageDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GeneratePresignedUrlUseCase {
    private final ImageMapper imageMapper;
    private final ImageDomainService imageDomainService;

    public PresignedUrlResponse execute(
            final ImageType imageType, final ImageFileExtension imageFileExtension) {
        final Long userId = SecurityUtils.getCurrentUserId();
        PresignedUrl presignedUrl =
                imageDomainService.generatePresignedUrl(userId, imageType, imageFileExtension);
        return imageMapper.toPresignedUrlResponse(presignedUrl);
    }
}
