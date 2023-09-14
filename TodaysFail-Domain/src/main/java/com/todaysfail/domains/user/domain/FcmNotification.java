package com.todaysfail.domains.user.domain;

import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class FcmNotification {
    private String fcmToken = "";

    private Boolean pushAlarm;

    private Boolean eventAlarm;

    public FcmNotification updateToken(String fcmToken) {
        return FcmNotification.builder()
                .fcmToken(fcmToken)
                .pushAlarm(this.pushAlarm)
                .eventAlarm(this.eventAlarm)
                .build();
    }

    public FcmNotification disableAlarm() {
        return FcmNotification.builder()
                .fcmToken(this.fcmToken)
                .pushAlarm(false)
                .eventAlarm(false)
                .build();
    }
}
