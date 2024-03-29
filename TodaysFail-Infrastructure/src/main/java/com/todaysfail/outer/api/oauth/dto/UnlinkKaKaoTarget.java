package com.todaysfail.outer.api.oauth.dto;

import lombok.Getter;

@Getter
public class UnlinkKaKaoTarget {

    @feign.form.FormProperty("target_id_type")
    private String targetIdType = "user_id";

    @feign.form.FormProperty("target_id")
    private String aud;

    public UnlinkKaKaoTarget(String aud) {
        this.aud = aud;
    }

    public static UnlinkKaKaoTarget from(String aud) {
        return new UnlinkKaKaoTarget(aud);
    }
}
