package com.todaysfail.domains.user.domain;

import com.todaysfail.common.type.user.OauthProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OauthInfoVo {
    private OauthProvider provider;
    private String oid;
}
