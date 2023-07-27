package com.todaysfail.domains.user.usecase;

import com.todaysfail.domains.user.domain.OauthInfoVo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;

public interface UserRegisterUseCase {
    User execute(Profile profile, OauthInfoVo oauthInfoVo);
}
