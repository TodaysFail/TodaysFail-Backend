package com.todaysfail.domains.auth.domain;

public record OauthToken(String accessToken, String refreshToken, String idToken) {}
