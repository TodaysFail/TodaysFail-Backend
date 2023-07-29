package com.todaysfail.events.handler;

import static org.mockito.BDDMockito.*;

import com.todaysfail.DomainIntegrateProfileResolver;
import com.todaysfail.DomainIntegrateTestConfig;
import com.todaysfail.common.type.user.OauthProvider;
import com.todaysfail.domains.user.domain.OauthInfo;
import com.todaysfail.domains.user.domain.Profile;
import com.todaysfail.domains.user.service.UserRegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = DomainIntegrateTestConfig.class)
@ActiveProfiles(resolver = DomainIntegrateProfileResolver.class)
class UserRegisterEventHandlerTest {
    @Autowired private UserRegisterService userRegisterService;

    @MockBean private UserRegisterEventHandler userRegisterEventHandler;

    @Test
    void 유저_생성_이벤트가_정상적으로_핸들링_되어야_한다() {
        // given
        Profile profile = Profile.from("name");
        OauthInfo oauthInfo = OauthInfo.of(OauthProvider.KAKAO, "oid");
        // when
        userRegisterService.execute(profile, oauthInfo);
        // then
        then(userRegisterEventHandler).should(times(1)).handleUserRegisterEvent(any());
    }
}