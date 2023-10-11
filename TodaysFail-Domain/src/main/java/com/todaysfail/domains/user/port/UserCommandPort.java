package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;

public interface UserCommandPort {
    User queryUser(Long userId);

    User queryUser(OauthInfo oauthInfo);

    User save(User user);
}
