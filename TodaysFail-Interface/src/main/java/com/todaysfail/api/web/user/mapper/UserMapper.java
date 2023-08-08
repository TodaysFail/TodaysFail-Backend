package com.todaysfail.api.web.user.mapper;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.common.annotation.Mapper;
import com.todaysfail.domains.nickname.domain.Nickname;

@Mapper
public class UserMapper {
    public RandomNicknameResponse toRandomNicknameResponse(Nickname nickname) {
        return new RandomNicknameResponse(nickname.getNickname(), nickname.getProfileImageUrl());
    }
}
