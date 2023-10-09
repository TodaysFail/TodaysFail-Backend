package com.todaysfail.api.web.user.usecase;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.api.web.user.mapper.UserMapper;
import com.todaysfail.common.annotation.UseCase;
import com.todaysfail.domains.user.domain.Nickname;
import com.todaysfail.domains.user.service.UserDomainService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RandomNicknameUseCase {
    private final UserMapper userMapper;
    private final UserDomainService userDomainService;

    public RandomNicknameResponse execute() {
        Nickname randomNickname = userDomainService.randomNicknameGeneration();
        return userMapper.toRandomNicknameResponse(randomNickname);
    }
}
