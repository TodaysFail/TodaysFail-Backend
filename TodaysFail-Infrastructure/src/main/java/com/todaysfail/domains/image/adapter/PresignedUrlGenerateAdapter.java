package com.todaysfail.domains.image.adapter;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.todaysfail.common.annotation.Adapter;
import com.todaysfail.domains.image.port.PresignedUrlGeneratePort;
import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class PresignedUrlGenerateAdapter implements PresignedUrlGeneratePort {
    private final AmazonS3 amazonS3;

    @Override
    public String generatePresignedUrl(String fileName, GeneratePresignedUrlRequest request) {
        return amazonS3.generatePresignedUrl(request).toString();
    }
}
