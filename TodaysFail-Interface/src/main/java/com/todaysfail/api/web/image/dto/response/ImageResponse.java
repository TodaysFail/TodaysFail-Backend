package com.todaysfail.api.web.image.dto.response;

public record ImageResponse(String imageUrl) {
    public static ImageResponse from(String imageUrl) {
        return new ImageResponse(imageUrl);
    }
}
