package com.todaysfail.common.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenInfo {
    private final Long userId;
    private final String role;
}
