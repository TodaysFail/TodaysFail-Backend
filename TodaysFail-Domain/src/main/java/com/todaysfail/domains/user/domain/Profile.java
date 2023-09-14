package com.todaysfail.domains.user.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@Builder
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Profile {
    private String name;

    private String profileImg;

    private Boolean isDefaultImg;

    public Profile withDraw() {
        return Profile.builder().name("탈퇴한 유저").profileImg("").isDefaultImg(true).build();
    }
}
