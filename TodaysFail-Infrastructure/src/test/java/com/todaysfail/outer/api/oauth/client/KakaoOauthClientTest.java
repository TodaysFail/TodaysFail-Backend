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
@TestPropertySource(properties = "feign.kakao.oauth=http://localhost:${wiremock.server.port}")
class KakaoOauthClientTest {
    @Autowired KakaoOauthClient kakaoOauthClient;

    @Test
    @DisplayName("카카오 OAuth 토큰 요청이 올바르게 파싱되어야한다")
    void 카카오_OAuth_토큰_요청이_올바르게_파싱되어야한다() throws IOException {
        // given
        Path filePath =
                ResourceUtils.getFile("classpath:payload/oauth-token-response.json").toPath();
        WireMock.stubFor(
                WireMock.post(WireMock.urlPathEqualTo("/oauth/token"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK)
                                        .withHeader(
                                                "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(Files.readAllBytes(filePath))));

        // when
        var response =
                kakaoOauthClient.kakaoAuth("CLIENT_ID", "REDIRECT_URI", "CODE", "CLIENT_SECRET");
        assertEquals(response.getIdToken(), "idToken");
        assertEquals(response.getAccessToken(), "accessToken");
        assertEquals(response.getRefreshToken(), "refreshToken");
    }

    @Test
    @DisplayName("카카오 OIDC 공개키 요청이 올바르게 파싱되어야한다")
    void 카카오_OIDC_공개키_요청이_올바르게_파싱되어야한다() throws IOException {
        var file =
                ResourceUtils.getFile("classpath:payload/oauth-oidc-public-key-response.json")
                        .toPath();
        WireMock.stubFor(
                WireMock.get(WireMock.urlPathEqualTo("/.well-known/jwks.json"))
                        .willReturn(
                                WireMock.aResponse()
                                        .withStatus(HttpStatus.OK)
                                        .withHeader(
                                                "Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                        .withBody(Files.readAllBytes(file))));
        var response = kakaoOauthClient.kakaoOIDCOpenKeys();
        assertEquals(response.getKeys().get(0).kid(), "kid1");
        assertEquals(response.getKeys().get(1).kid(), "kid2");
    }
}
