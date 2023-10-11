package com.todaysfail.domains.image.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.todaysfail.common.consts.TodaysFailConst;
import com.todaysfail.common.helper.SpringEnvironmentHelper;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.config.properties.AwsS3Properties;
import com.todaysfail.domains.image.domain.Image;
import com.todaysfail.domains.image.domain.PresignedUrl;
import com.todaysfail.domains.image.port.ImageCommandPort;
import com.todaysfail.domains.image.port.PresignedUrlGeneratePort;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageDomainService {
    private final AwsS3Properties awsS3Properties;
    private final SpringEnvironmentHelper springEnvironmentHelper;
    private final PresignedUrlGeneratePort presignedUrlGeneratePort;
    private final ImageCommandPort imageCommandPort;

    public PresignedUrl generatePresignedUrl(
            Long userId, ImageType imageType, ImageFileExtension imageFileExtension) {
        String imageKey = UUID.randomUUID().toString();
        String fileName = getFileName(userId, imageType, imageFileExtension, imageKey);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                getGeneratePreSignedUrlRequest(
                        awsS3Properties.getBucket(),
                        fileName,
                        imageFileExtension.getUploadExtension());

        String presignedUrl =
                presignedUrlGeneratePort.generatePresignedUrl(
                        fileName, generatePresignedUrlRequest);
        return PresignedUrl.of(presignedUrl, imageKey);
    }

    public String uploadSuccess(
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
        return imageCommandPort
                .save(
                        Image.builder()
                                .userId(userId)
                                .imageType(imageType)
                                .imageFileExtension(imageFileExtension)
                                .imageKey(imageKey)
                                .imageUrl(imageUrl)
                                .build())
                .getImageUrl();
    }

    private String getFileName(
            Long userId,
            ImageType imageType,
            ImageFileExtension imageFileExtension,
            String imageKey) {
        return springEnvironmentHelper.getCurrentProfile()
                + "/"
                + imageType.getValue()
                + "/"
                + userId
                + "/"
                + imageKey
                + "."
                + imageFileExtension.getUploadExtension();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(
            String bucket, String fileName, String fileExtension) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName, HttpMethod.PUT)
                        .withKey(fileName)
                        .withContentType("image/" + fileExtension)
                        .withExpiration(getPreSignedUrlExpiration());

        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());

        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        var expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 30;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
