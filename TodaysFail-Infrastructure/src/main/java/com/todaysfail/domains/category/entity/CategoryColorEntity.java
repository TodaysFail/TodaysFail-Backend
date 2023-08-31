package com.todaysfail.domains.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "tbl_category_color")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_color_id")
    private Long id;

    private String colorName;

    private String colorCode;
}
