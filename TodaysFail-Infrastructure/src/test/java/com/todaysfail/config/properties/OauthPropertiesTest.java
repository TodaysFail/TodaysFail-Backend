package com.todaysfail.config.properties;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import com.todaysfail.InfraIntegrateSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@InfraIntegrateSpringBootTest
class OauthPropertiesTest {
    @Autowired private OauthProperties oauthProperties;

    @Test
    void oauth_프로퍼티가_정상적으로_init_되어야한다() {
        assertEquals(oauthProperties.getKakao().getAppId(), "default");
    }
}
