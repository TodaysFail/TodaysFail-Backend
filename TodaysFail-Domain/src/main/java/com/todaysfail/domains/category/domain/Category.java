package com.todaysfail.domains.category.domain;

import com.todaysfail.domains.category.exception.CategoryNotOwnedByUserException;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity(name = "tbl_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private Long userId;

    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryColor categoryColor;

    public void modify(String categoryName, CategoryColor categoryColor) {
        this.name = categoryName;
        this.categoryColor = categoryColor;
    }

    public void validateOwnership(Long userId) {
        if (!this.userId.equals(userId)) {
            throw CategoryNotOwnedByUserException.EXCEPTION;
        }
    }
}
