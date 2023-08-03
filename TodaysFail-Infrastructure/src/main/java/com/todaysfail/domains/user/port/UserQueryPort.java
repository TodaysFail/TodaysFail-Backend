package com.todaysfail.domains.user.port;

import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.entity.UserEntity;
import java.util.Optional;

public interface UserQueryPort {
    Optional<UserEntity> findByOauthInfo(String oauthId, OauthProvider provider);

    Boolean existsByOauthInfo(String oauthId, OauthProvider provider);
}
