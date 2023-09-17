package com.todaysfail.domains.image.port;

import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

public interface PresignedUrlGeneratePort {
    String generatePresignedUrl(String fileName, GeneratePresignedUrlRequest request);
}
