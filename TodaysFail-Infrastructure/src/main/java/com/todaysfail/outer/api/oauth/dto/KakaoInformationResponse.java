package com.todaysfail.outer.api.oauth.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoInformationResponse {
    private String id;
    private KakaoAccount kakaoAccount;

    @Getter
    @NoArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    public static class KakaoAccount {
        private Profile profile;
        private String name;

        @Getter
        @NoArgsConstructor
        @JsonNaming(SnakeCaseStrategy.class)
        public static class Profile {
            private String profileImageUrl;
            private Boolean isDefaultImage;
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

    public String getName() {
        return kakaoAccount.getName();
    }

    public String getProfileUrl() {
        return kakaoAccount.getProfileImageUrl();
    }

    public Boolean getIsDefaultImage() {
        return kakaoAccount.getProfile().getIsDefaultImage();
    }
}
