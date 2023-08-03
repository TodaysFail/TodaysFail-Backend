package com.todaysfail.domains.auth.usecase;

import com.todaysfail.domains.auth.domain.OauthUserInfo;

public interface OauthUserInfoUseCase {
    OauthUserInfo execute(String accessToken);
}
