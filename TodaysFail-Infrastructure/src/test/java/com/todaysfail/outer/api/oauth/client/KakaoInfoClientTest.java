package com.todaysfail.outer.api.oauth.client;

import static org.junit.jupiter.api.Assertions.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.todaysfail.InfraIntegrateProfileResolver;
import com.todaysfail.InfraIntegrateTestConfig;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.spec.internal.HttpStatus;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;

@ContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = InfraIntegrateTestConfig.class)
@AutoConfigureWireMock(port = 0)
@ActiveProfiles(resolver = InfraIntegrateProfileResolver.class)
@TestPropertySource(properties = "feign.kakao.info=http://localhost:${wiremock.server.port}")
class KakaoInfoClientTest {
    @Autowired KakaoInfoClient kakaoInfoClient;

    @Test
    @DisplayName("카카오 유저 정보 요청이 올바르게 파싱되어야한다")
    void 카카오_유저_정보_요청이_올바르게_파싱되어야한다() throws IOException {
        // given
        Path filePath =
                ResourceUtils.getFile("classpath:payload/oauth-user-info-response.json").toPath();
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/v2/user/me"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK)
                                        .withHeader(
                                                "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(Files.readAllBytes(filePath))));

        // when
        var response = kakaoInfoClient.kakaoUserInfo("accessToken");

        // then
        assertEquals(response.getKakaoAccount().getName(), "sample@sample.com");
        assertEquals(
                response.getKakaoAccount().getProfileImageUrl(),
                "http://yyy.kakao.com/dn/.../img_640x640.jpg");
        assertEquals(response.getKakaoAccount().getIsDefaultImage(), false);
    }
}
