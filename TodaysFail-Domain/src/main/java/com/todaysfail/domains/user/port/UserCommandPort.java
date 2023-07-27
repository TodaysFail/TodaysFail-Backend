package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.domain.OauthInfoVo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;

public interface UserCommandPort {
    User registerUser(Profile profile, OauthInfoVo oauthInfoVo);
}
