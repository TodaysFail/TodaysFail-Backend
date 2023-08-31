package com.todaysfail.domains.category.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class CategoryColorNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new CategoryColorNotFoundException();

    public CategoryColorNotFoundException() {
        super(CategoryErrorCode.CATEGORY_COLOR_NOT_FOUND);
    }
}
