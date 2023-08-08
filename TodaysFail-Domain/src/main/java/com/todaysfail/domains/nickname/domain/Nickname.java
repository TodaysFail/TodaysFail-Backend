package com.todaysfail.domains.nickname.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Nickname {
    private String nickname;
    private String profileImageUrl;

    public static Nickname of(String nickname, String profileImageUrl) {
        return new Nickname(nickname, profileImageUrl);
    }
}
