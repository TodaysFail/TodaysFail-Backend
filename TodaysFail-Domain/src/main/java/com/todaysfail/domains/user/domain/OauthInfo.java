package com.todaysfail.domains.user.domain;

import com.todaysfail.common.type.user.OauthProvider;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OauthInfo {
    private OauthProvider provider;
    private String oid;

    public static OauthInfo of(OauthProvider provider, String oid) {
        return new OauthInfo(provider, oid);
    }
}
