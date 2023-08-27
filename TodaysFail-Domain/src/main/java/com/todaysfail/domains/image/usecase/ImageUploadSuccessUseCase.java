package com.todaysfail.domains.image.usecase;

import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;

public interface ImageUploadSuccessUseCase {
    String success(
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey);
}
