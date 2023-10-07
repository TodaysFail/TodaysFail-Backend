package com.todaysfail.domains.category.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CategoryColor {
    BLACK("BLACK", "#000000"),
    ;

    private String color;
    private String code;
}