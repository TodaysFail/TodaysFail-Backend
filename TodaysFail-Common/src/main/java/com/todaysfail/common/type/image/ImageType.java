package com.todaysfail.common.type.image;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageType {
    PROFILE("profile"),
    ;

    private String value;
}
