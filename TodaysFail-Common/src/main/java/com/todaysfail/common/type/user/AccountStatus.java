package com.todaysfail.common.type.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {
    NORMAL("NORMAL"),
    DELETED("DELETED"),
    FORBIDDEN("FORBIDDEN");

    private String value;
}
