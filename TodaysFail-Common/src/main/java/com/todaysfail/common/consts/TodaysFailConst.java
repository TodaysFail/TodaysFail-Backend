package com.todaysfail.common.consts;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class TodaysFailConst {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String WITHDRAW_PREFIX = "DELETED:";
    public static final String TOKEN_ROLE = "role";
    public static final String TOKEN_TYPE = "type";
    public static final String TOKEN_ISSUER = "TodaysFail";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public static final String REFRESH_TOKEN = "REFRESH_TOKEN";
    public static final String RECOMMEND_TAG_KEY = "RecommendTag";

    public static final int MILLI_TO_SECOND = 1000;
    public static final int SERVICE_UNAVAILABLE = 503;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int TOO_MANY_REQUESTS = 429;
    public static final int INTERNAL_SERVER = 500;

    public static final int NICKNAME_GENERATE_MAX_ATTEMPTS = 10;

    public static final String PROD_SERVER_URL = "https://todaysfail.com";
    public static final String DEV_SERVER_URL = "https://dev.todaysfail.com";
    public static final String LOCAL_SERVER_URL = "http://localhost:8080";

    public static final String IMAGE_DOMAIN = "https://image.todaysfail.com";

    public static final String[] SwaggerPatterns = {
        "/swagger-resources/**", "/swagger-ui/**", "/v3/api-docs/**", "/v3/api-docs",
    };
}
