package com.todaysfail.domains.user.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FcmNotification {
    private String fcmToken;
    private Boolean pushAlarm;
    private Boolean eventAlarm;

    public static FcmNotification of(String fcmToken, Boolean pushAlarm, Boolean eventAlarm) {
        return new FcmNotification(fcmToken, pushAlarm, eventAlarm);
    }

    public static FcmNotification updateToken(FcmNotification originalState, String fcmToken) {
        return new FcmNotification(fcmToken, originalState.pushAlarm, originalState.eventAlarm);
    }

    public void withDraw() {
        this.fcmToken = "";
        this.pushAlarm = false;
        this.eventAlarm = false;
    }
}
