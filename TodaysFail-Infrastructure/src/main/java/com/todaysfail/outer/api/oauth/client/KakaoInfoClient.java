package com.todaysfail.outer.api.oauth.client;

import com.todaysfail.outer.api.oauth.dto.KakaoInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "KakaoInfoClient", url = "${feign.kakao.info}")
public interface KakaoInfoClient {
    @GetMapping("/v2/user/me")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}
