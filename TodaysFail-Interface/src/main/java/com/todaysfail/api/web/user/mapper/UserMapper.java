package com.todaysfail.api.web.user.mapper;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.common.vo.UserDetail;
import com.todaysfail.domains.user.domain.Nickname;
import com.todaysfail.domains.user.domain.User;

@Mapper
public class UserMapper {
    public RandomNicknameResponse toRandomNicknameResponse(Nickname nickname) {
        return new RandomNicknameResponse(nickname.getNickname(), nickname.getProfileImageUrl());
    }

    public UserDetail toUserDetail(User user) {
        return UserDetail.from(user);
    }
}
