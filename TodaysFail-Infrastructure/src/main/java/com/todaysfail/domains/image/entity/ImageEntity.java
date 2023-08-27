package com.todaysfail.domains.image.entity;

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

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private String imageKey;

    private String imageUri;

    private ImageEntity(Long id, ImageType imageType, String imageKey, String imageUri) {
        this.id = id;
        this.imageType = imageType;
        this.imageKey = imageKey;
        this.imageUri = imageUri;
    }

    public static ImageEntity registerImage(ImageType imageType, String imageKey, String imageUri) {
        return new ImageEntity(null, imageType, imageKey, imageUri);
    }

    public static ImageEntity from(Long id, ImageType imageType, String imageKey, String imageUri) {
        return new ImageEntity(id, imageType, imageKey, imageUri);
    }
}
