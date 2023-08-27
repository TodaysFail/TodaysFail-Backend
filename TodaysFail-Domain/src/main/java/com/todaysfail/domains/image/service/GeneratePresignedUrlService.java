package com.todaysfail.domains.image.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.todaysfail.common.helper.SpringEnvironmentHelper;
import com.todaysfail.common.type.image.ImageFileExtension;
import com.todaysfail.common.type.image.ImageType;
import com.todaysfail.config.properties.AwsS3Properties;
import com.todaysfail.domains.image.domain.PresignedUrl;
import com.todaysfail.domains.image.port.PresignedUrlGeneratePort;
import com.todaysfail.domains.image.usecase.GeneratePresignedUrlUseCase;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratePresignedUrlService implements GeneratePresignedUrlUseCase {
    private final AwsS3Properties awsS3Properties;
    private final SpringEnvironmentHelper springEnvironmentHelper;
    private final PresignedUrlGeneratePort presignedUrlGeneratePort;

    @Override
    public PresignedUrl execute(
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
