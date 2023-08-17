package com.todaysfail.common.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("common")
class PropertiesTest {
    @Autowired private OauthProperties oauthProperties;

    @Autowired private JwtProperties jwtProperties;

    @Test
    void oauth_프로퍼티가_정상적으로_init_되어야한다() {
        assertEquals(oauthProperties.getKakao().getAppId(), "default");
    }

    @Test
    void jwt_프로퍼티가_정상적으로_init_되어야한다() {
        Long accessExp = jwtProperties.getAccessExp();
        Long refreshExp = jwtProperties.getRefreshExp();
        assertEquals(accessExp, 3600);
        assertEquals(refreshExp, 3600);
    }
}
