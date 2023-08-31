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
    private String colorName;
    private String colorCode;

    public static CategoryColor of(Long categoryColorId, String colorName, String colorCode) {
        return new CategoryColor(categoryColorId, colorName, colorCode);
    }
}
