package com.todaysfail.api.web.auth.dto.response;

public record OauthTokenResponse(String accessToken, String refreshToken, String idToken) {}
