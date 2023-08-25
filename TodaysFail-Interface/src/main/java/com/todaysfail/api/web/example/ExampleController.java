package com.todaysfail.api.web.example;

import com.todaysfail.common.annotation.ApiErrorCodeExample;
import com.todaysfail.common.annotation.DevelopOnly;
import com.todaysfail.common.exception.GlobalErrorCode;
import com.todaysfail.domains.user.exception.UserErrorCode;
import com.todaysfail.outer.api.oauth.exception.KakaoOauthErrorCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "X. [예시]")
@RestController
@RequestMapping("/api/v1/examples")
@RequiredArgsConstructor
public class ExampleController {

    @GetMapping("/health")
    @Operation(summary = "헬스체크")
    public void health() {
        // health check
    }

    @GetMapping("/global")
    @DevelopOnly
    @Operation(summary = "글로벌 ( 인증 , aop, 서버 내부 오류등)  관련 에러 코드 나열")
    @ApiErrorCodeExample(GlobalErrorCode.class)
    public void getGlobalErrorCode() {}

    @GetMapping("/user")
    @DevelopOnly
    @Operation(summary = "유저 도메인 관련 에러 코드 나열")
    @ApiErrorCodeExample(UserErrorCode.class)
    public void getUserErrorCode() {}

    @GetMapping("/kakao")
    @DevelopOnly
    @Operation(summary = "카카오 에러 코드 나열")
    @ApiErrorCodeExample(KakaoOauthErrorCode.class)
    public void getKakaoOauthErrorCode() {}
}
