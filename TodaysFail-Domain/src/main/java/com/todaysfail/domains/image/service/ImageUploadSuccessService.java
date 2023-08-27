package com.todaysfail.domains.image.service;

import com.todaysfail.common.consts.TodaysFailConst;
import com.todaysfail.common.helper.SpringEnvironmentHelper;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.domains.image.domain.Image;
import com.todaysfail.domains.image.entity.ImageEntity;
import com.todaysfail.domains.image.port.ImageCommandPort;
import com.todaysfail.domains.image.usecase.ImageUploadSuccessUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageUploadSuccessService implements ImageUploadSuccessUseCase {
    private final ImageCommandPort imageCommandPort;
    private final SpringEnvironmentHelper springEnvironmentHelper;

    @Override
    public String success(
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey) {
        String imageUrl =
                TodaysFailConst.IMAGE_DOMAIN
                        + "/"
                        + springEnvironmentHelper.getCurrentProfile()
                        + "/"
                        + imageType.getValue()
                        + "/"
                        + userId
                        + "/"
                        + imageKey
                        + "."
                        + imageFileExtension.getUploadExtension();
        ImageEntity imageEntity =
                ImageEntity.registerImage(
                        userId, imageType, imageFileExtension, imageKey, imageUrl);
        Image image = Image.registerImage(imageEntity);
        imageCommandPort.save(imageEntity);
        return image.getImageUrl();
    }
}
