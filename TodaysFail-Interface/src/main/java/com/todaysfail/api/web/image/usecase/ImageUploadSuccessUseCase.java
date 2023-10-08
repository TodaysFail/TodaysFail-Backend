package com.todaysfail.api.web.image.usecase;

import com.todaysfail.api.web.image.dto.response.ImageResponse;
import com.todaysfail.api.web.image.mapper.ImageMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.image.service.ImageDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ImageUploadSuccessUseCase {
    private final ImageMapper imageMapper;
    private final ImageDomainService imageDomainService;

    public ImageResponse execute(
            ImageType imageType, ImageFileExtension imageFileExtension, String imageKey) {
        Long userId = SecurityUtils.getCurrentUserId();
        return imageMapper.toImageResponse(
                imageDomainService.uploadSuccess(userId, imageType, imageFileExtension, imageKey));
    }
}
