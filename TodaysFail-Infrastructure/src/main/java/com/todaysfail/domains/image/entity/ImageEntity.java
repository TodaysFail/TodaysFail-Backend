package com.todaysfail.domains.image.entity;

import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.domains.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    @Enumerated(EnumType.STRING)
    private ImageFileExtension imageFileExtension;

    private String imageKey;

    private String imageUrl;

    private ImageEntity(
            Long id,
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey,
            String imageUrl) {
        this.id = id;
        this.userId = userId;
        this.imageType = imageType;
        this.imageFileExtension = imageFileExtension;
        this.imageKey = imageKey;
        this.imageUrl = imageUrl;
    }

    public static ImageEntity registerImage(
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey,
            String imageUrl) {
        return new ImageEntity(null, userId, imageType, imageFileExtension, imageKey, imageUrl);
    }

    public static ImageEntity of(
            Long id,
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey,
            String imageUrl) {
        return new ImageEntity(id, userId, imageType, imageFileExtension, imageKey, imageUrl);
    }
}
