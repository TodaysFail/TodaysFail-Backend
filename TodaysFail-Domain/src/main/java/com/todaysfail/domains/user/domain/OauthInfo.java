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
    private String oauthId;
    private OauthProvider provider;

    public static OauthInfo of(String oauthId, OauthProvider provider) {
        return new OauthInfo(oauthId, provider);
    }
}
