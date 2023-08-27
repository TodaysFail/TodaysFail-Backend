package com.todaysfail.domains.image.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PresignedUrl {
    private String presignUrl;
    private String imageKey;

    public static PresignedUrl of(String presignUrl, String imageKey) {
        return new PresignedUrl(presignUrl, imageKey);
    }
}
