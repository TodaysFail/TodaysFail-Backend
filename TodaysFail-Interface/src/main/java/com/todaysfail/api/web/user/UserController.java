package com.todaysfail.api.web.user;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.api.web.user.mapper.UserMapper;
import com.todaysfail.config.security.SecurityUtils;
import com.todaysfail.domains.user.domain.UserDetail;
import com.todaysfail.domains.user.usecase.QueryUserUseCase;
import com.todaysfail.domains.user.usecase.RandomNicknameUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "2. [유저]")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserMapper userMapper;
    private final QueryUserUseCase queryUserUseCase;
    private final RandomNicknameUseCase randomNicknameUseCase;

    @SecurityRequirement(name = "access-token")
    @Operation(summary = "내 정보를 조회합니다.")
    @GetMapping("/me")
    public UserDetail queryMyInfo() {
        return queryUserUseCase.queryMyInfo(SecurityUtils.getCurrentUserId());
    }

    @Operation(summary = "사용 가능 한 랜덤 닉네임을 발급합니다.")
    @GetMapping("/nickname/generate")
    public RandomNicknameResponse generateRandomNickname() {
        return userMapper.toRandomNicknameResponse(randomNicknameUseCase.execute());
    }
}
