package com.todaysfail.api.web.auth;

import com.todaysfail.api.web.auth.dto.request.RegisterRequest;
import com.todaysfail.api.web.auth.dto.response.AbleRegisterResponse;
import com.todaysfail.api.web.auth.dto.response.OauthLoginLinkResponse;
import com.todaysfail.api.web.auth.dto.response.OauthTokenResponse;
import com.todaysfail.api.web.auth.dto.response.OauthUserInfoResponse;
import com.todaysfail.api.web.auth.dto.response.TokenAndUserResponse;
import com.todaysfail.api.web.auth.mapper.AuthMapper;
import com.todaysfail.domains.auth.domain.OauthUserInfo;
import com.todaysfail.domains.auth.domain.TokenAndUser;
import com.todaysfail.domains.auth.usecase.OauthUserInfoUseCase;
import com.todaysfail.domains.auth.usecase.RegisterUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "1-1. [인증]")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthMapper authMapper;
    private final RegisterUserUseCase registerUserUseCase;
    private final OauthUserInfoUseCase oauthUserInfoUseCase;

    @Operation(summary = "개발용 회원가입입니다 ( 백엔드용 )", deprecated = true)
    @GetMapping("/oauth/kakao/develop")
    public TokenAndUserResponse developUserSign(@RequestParam("code") String code) {
        return authMapper.toTokenAndUserResponse(registerUserUseCase.upsertKakaoOauthUser(code));
    }

    @Operation(summary = "kakao oauth 링크발급 ( 백엔드용 )", description = "kakao 링크를 받아볼수 있습니다.")
    @GetMapping("/oauth/kakao/link/test")
    public OauthLoginLinkResponse getKakaoOauthLink() {
        return authMapper.toOauthLoginLinkResponse(registerUserUseCase.getKaKaoOauthLink());
    }

    @Operation(summary = "카카오 code 요청받는 곳입니다. ( 백엔드 용 ) ")
    @GetMapping("/oauth/kakao")
    public OauthTokenResponse getCredentialFromKaKao(@RequestParam("code") String code) {
        return authMapper.toOauthTokenResponse(registerUserUseCase.getCredentialFromKaKao(code));
    }

    @Operation(summary = "id_token 으로 회원가입을 합니다.")
    @PostMapping("/oauth/kakao/register")
    public TokenAndUserResponse kakaoAuthCheckRegisterValid(
            @RequestParam("id_token") String token,
            @Valid @RequestBody RegisterRequest registerRequest) {
        TokenAndUser tokenAndUser =
                registerUserUseCase.registerUserByOCIDToken(
                        token,
                        registerRequest.profileImage(),
                        registerRequest.isDefaultImage(),
                        registerRequest.name(),
                        registerRequest.fcmToken(),
                        registerRequest.pushAlarm(),
                        registerRequest.eventAlarm());
        return authMapper.toTokenAndUserResponse(tokenAndUser);
    }

    @Operation(summary = "회원가입이 가능한지 id token 으로 확인합니다.")
    @GetMapping("/oauth/kakao/register/valid")
    public AbleRegisterResponse kakaoAuthCheckRegisterValid(
            @RequestParam("id_token") String token) {
        return authMapper.toAbleRegisterResponse(registerUserUseCase.checkAvailableRegister(token));
    }

    @Operation(summary = "accessToken 으로 oauth user 정보를 가져옵니다.")
    @PostMapping("/oauth/kakao/info")
    public OauthUserInfoResponse kakaoOauthUserInfo(
            @RequestParam("access_token") String accessToken) {
        OauthUserInfo oauthUserInfo = oauthUserInfoUseCase.execute(accessToken);
        return authMapper.toOauthUserInfoResponse(oauthUserInfo);
    }
}
