package com.todaysfail.domains.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_category")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private Long userId;

    private String name;

    private Long categoryColorId;

    public static CategoryEntity registerCategory(Long userId, String name, Long categoryColorId) {
        return new CategoryEntity(null, userId, name, categoryColorId);
    }

    public void modify(String categoryName, Long categoryColorId) {
        this.name = categoryName;
        this.categoryColorId = categoryColorId;
    }
}
