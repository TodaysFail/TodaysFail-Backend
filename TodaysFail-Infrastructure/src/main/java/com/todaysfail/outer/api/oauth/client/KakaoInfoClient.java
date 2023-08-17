package com.todaysfail.outer.api.oauth.client;

import com.todaysfail.outer.api.oauth.config.KakaoInfoConfig;
import com.todaysfail.outer.api.oauth.dto.KakaoInformationResponse;
import com.todaysfail.outer.api.oauth.dto.UnlinkKaKaoTarget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "${feign.kakao.info}",
        configuration = KakaoInfoConfig.class)
public interface KakaoInfoClient {
    @GetMapping("/v2/user/me")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);

    @PostMapping(path = "/v1/user/unlink", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    void unlinkUser(
            @RequestHeader("Authorization") String adminKey, UnlinkKaKaoTarget unlinkKaKaoTarget);
}
