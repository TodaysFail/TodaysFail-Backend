package com.todaysfail.domains.user.usecase;

import com.todaysfail.domains.user.domain.FcmNotification;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.domain.User;

public interface UserRegisterUseCase {
    User execute(Profile profile, OauthInfo oauthInfo, FcmNotification fcmNotification);
}
