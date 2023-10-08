package com.todaysfail.api.web.image.mapper;

import com.todaysfail.api.web.image.dto.response.ImageResponse;
import com.todaysfail.api.web.image.dto.response.PresignedUrlResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.image.domain.PresignedUrl;

@Mapper
public class ImageMapper {
    public PresignedUrlResponse toPresignedUrlResponse(PresignedUrl presignedUrl) {
        return PresignedUrlResponse.of(presignedUrl.getPresignUrl(), presignedUrl.getImageKey());
    }

    public ImageResponse toImageResponse(String imageUrl) {
        return ImageResponse.from(imageUrl);
    }
}
