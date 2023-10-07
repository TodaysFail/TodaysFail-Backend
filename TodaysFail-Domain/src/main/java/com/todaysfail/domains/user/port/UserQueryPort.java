package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;

public interface UserQueryPort {
    User findByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByName(String name);

    User queryUser(Long userId);
}
