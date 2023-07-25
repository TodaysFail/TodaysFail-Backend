package com.todaysfail.common.type.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;
}
