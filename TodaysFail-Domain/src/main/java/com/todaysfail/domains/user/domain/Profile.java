package com.todaysfail.domains.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {
    private String name;
    private String profileImg;
    private Boolean isDefaultImg;

    public static Profile from(String name, String profileImg, Boolean isDefaultImg) {
        return new Profile(name, profileImg, isDefaultImg);
    }

    public void withDraw() {
        this.name = "탈퇴한 사용자";
        this.profileImg = "";
        this.isDefaultImg = true;
    }
}
