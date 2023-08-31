package com.todaysfail.domains.category.domain;

import com.todaysfail.domains.category.entity.CategoryColorEntity;
import com.todaysfail.domains.category.entity.CategoryEntity;
import com.todaysfail.domains.category.exception.CategoryNotOwnedByUserException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {
    private Long categoryId;
    private Long userId;
    private String categoryName;
    private CategoryColor categoryColor;

    public static Category of(
            CategoryEntity categoryEntity, CategoryColorEntity categoryColorEntity) {
        return new Category(
                categoryEntity.getId(),
                categoryEntity.getUserId(),
                categoryEntity.getName(),
                CategoryColor.of(
                        categoryColorEntity.getId(),
                        categoryColorEntity.getColorName(),
                        categoryColorEntity.getColorCode()));
    }

    /** 본인이 생성 한 카테고리인지 확인 */
    public void validateOwnership(Long userId) {
        if (!this.userId.equals(userId)) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
