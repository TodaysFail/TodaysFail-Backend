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
@Entity(name = "tbl_category_color")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_color_id")
    private Long id;

    private String colorName;

    private String colorCode;

    public static CategoryColorEntity registerCategoryColor(String colorCode, String colorName) {
        return new CategoryColorEntity(null, colorName, colorCode);
    }
}
