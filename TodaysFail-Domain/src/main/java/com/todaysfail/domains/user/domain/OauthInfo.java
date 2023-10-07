package com.todaysfail.domains.user.domain;

import static com.todaysfail.common.consts.TodaysFailConst.*;

import com.todaysfail.common.type.user.OauthProvider;
import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class OauthInfo {
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private OauthProvider provider;

    public OauthInfo withDraw() {
        return OauthInfo.builder()
                .oauthId(WITHDRAW_PREFIX + LocalDateTime.now() + ":" + oauthId)
                .provider(provider)
                .build();
    }
}
