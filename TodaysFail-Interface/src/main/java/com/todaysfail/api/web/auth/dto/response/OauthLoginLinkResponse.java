package com.todaysfail.api.web.auth.dto.response;

public record OauthLoginLinkResponse(String link) {
    public static OauthLoginLinkResponse from(String link) {
        return new OauthLoginLinkResponse(link);
    }
}
