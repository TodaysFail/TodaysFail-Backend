package com.todaysfail.domains.user.domain;

import com.todaysfail.common.type.user.AccountRole;
import com.todaysfail.common.type.user.AccountStatus;
import com.todaysfail.common.type.user.OauthProvider;

public record User(
        Long userId,
        String nickname,
        OauthProvider oauthProvider,
        String oid,
        AccountStatus accountStatus,
        AccountRole accountRole) {}
