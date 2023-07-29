package com.todaysfail.domains.user.port;

import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.entity.UserEntity;

public interface UserCommandPort {
    UserEntity registerUser(
            String name,
            OauthProvider provider,
            String oid,
            AccountStatus accountStatus,
            AccountRole accountRole);
}