package com.todaysfail.domains.image.usecase;

import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.domains.image.domain.PresignedUrl;

public interface GeneratePresignedUrlUseCase {

    PresignedUrl execute(Long userId, ImageType imageType, ImageFileExtension imageFileExtension);
}
