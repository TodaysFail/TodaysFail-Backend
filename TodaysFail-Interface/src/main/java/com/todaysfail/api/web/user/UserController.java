package com.todaysfail.api.web.user;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.api.web.user.mapper.UserMapper;
import com.todaysfail.domains.nickname.usecase.RandomNicknameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "1-2. [유저]")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final RandomNicknameUseCase randomNicknameUseCase;

    @Operation(summary = "사용 가능 한 랜덤 닉네임을 발급합니다.")
    @GetMapping("/nickname/generate")
    public RandomNicknameResponse generateRandomNickname() {
        return userMapper.toRandomNicknameResponse(randomNicknameUseCase.execute());
    }
}
