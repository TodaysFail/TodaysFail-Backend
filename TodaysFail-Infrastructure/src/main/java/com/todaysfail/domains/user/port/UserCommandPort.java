package com.todaysfail.domains.user.port;

import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.common.type.user.UserRole;
import com.todaysfail.common.type.user.UserStatus;
import com.todaysfail.domains.user.entity.UserEntity;

public interface UserCommandPort {
    UserEntity registerUser(
            String name,
            String profileImg,
            Boolean isDefaultImg,
            OauthProvider provider,
            String oid,
            UserStatus userStatus,
            UserRole userRole,
            String fcmToken,
            Boolean pushAlarm,
            Boolean eventAlarm);
}
