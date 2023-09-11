package com.todaysfail.domains.category.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryColor {
    private Long categoryColorId;
    private String colorCode;
    private String colorName;

    public static CategoryColor of(Long categoryColorId, String colorCode, String colorName) {
        return new CategoryColor(categoryColorId, colorCode, colorName);
    }
}
