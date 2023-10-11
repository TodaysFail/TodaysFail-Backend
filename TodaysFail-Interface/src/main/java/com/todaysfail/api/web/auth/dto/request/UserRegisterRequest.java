package com.todaysfail.api.web.auth.dto.request;

public record UserRegisterRequest(
        String profileImage,
        Boolean isDefaultImage,
        String name,
        String fcmToken,
        Boolean pushAlarm,
        Boolean eventAlarm) {}
