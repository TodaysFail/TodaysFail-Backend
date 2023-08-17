package com.todaysfail.domains.user.usecase;

import com.todaysfail.domains.user.domain.OauthInfo;

public interface UserCanRegisterCheckUseCase {
    Boolean checkUserCanRegister(OauthInfo oauthInfo);
}
