package com.todaysfail.domains.image.domain;

import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.domains.image.entity.ImageEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {
    private Long imageId;
    private ImageType imageType;
    private String imageKey;
    private String imageUri;

    private static Image from(ImageEntity userEntity) {
        return new Image(
                userEntity.getId(),
                userEntity.getImageType(),
                userEntity.getImageKey(),
                userEntity.getImageUri());
    }

    public static Image of(ImageType imageType, String imageKey, String imageUri) {
        return new Image(null, imageType, imageKey, imageUri);
    }

    public static Image registerUser(ImageEntity imageEntity) {
        return Image.from(imageEntity);
    }
}
