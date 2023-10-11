package com.todaysfail.api.web.user;

import com.todaysfail.api.web.user.dto.response.RandomNicknameResponse;
import com.todaysfail.api.web.user.usecase.RandomNicknameUseCase;
import com.todaysfail.api.web.user.usecase.UserQueryUseCase;
import com.todaysfail.api.web.user.usecase.UserWithDrawUseCase;
import com.todaysfail.common.annotation.DisableSwaggerSecurity;
import com.todaysfail.common.vo.UserDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "2. [유저]")
@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
public class UserController {
    private final UserQueryUseCase userQueryUseCase;
    private final RandomNicknameUseCase randomNicknameUseCase;
    private final UserWithDrawUseCase userWithDrawUseCase;

    @SecurityRequirement(name = "access-token")
    @Operation(summary = "내 정보를 조회합니다.")
    @GetMapping("/me")
    public UserDetail queryMyInfo() {
        return userQueryUseCase.queryMyInfo();
    }

    @SecurityRequirement(name = "access-token")
    @Operation(summary = "회원탈퇴")
    @DeleteMapping("/me")
    public void withdrawal() {
        userWithDrawUseCase.execute();
    }

    @DisableSwaggerSecurity
    @Operation(summary = "사용 가능 한 랜덤 닉네임을 발급합니다.")
    @GetMapping("/nickname/generate")
    public RandomNicknameResponse generateRandomNickname() {
        return randomNicknameUseCase.execute();
    }
}
