package com.todaysfail.outer.api.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
@ToString
public class KakaoInformationResponse {
    private String id;
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    @ToString
    public static class KakaoAccount {
        private Profile profile;

        @Getter
        @NoArgsConstructor
        @JsonNaming(SnakeCaseStrategy.class)
        @ToString
        public static class Profile {
            private String nickname;
            private String profileImageUrl;
            private Boolean isDefaultImage;
        }

        public String getNickname() {
            return profile.getNickname();
        }

        public String getProfileImageUrl() {
            return profile.getProfileImageUrl();
        }

        public Boolean getIsDefaultImage() {
            return profile.getIsDefaultImage();
        }
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return kakaoAccount.getProfile().getNickname();
    }

    public String getProfileImageUrl() {
        return kakaoAccount.getProfileImageUrl();
    }

    public Boolean getIsDefaultImage() {
        return kakaoAccount.getProfile().getIsDefaultImage();
    }
}
