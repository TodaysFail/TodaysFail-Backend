package com.todaysfail.domains.user.port;

import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.User;
import java.util.Optional;

public interface UserQueryPort {
    Optional<User> findByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByOauthInfo(OauthInfo oauthInfo);

    Boolean existsByName(String name);

    Optional<User> queryUser(Long userId);
}
