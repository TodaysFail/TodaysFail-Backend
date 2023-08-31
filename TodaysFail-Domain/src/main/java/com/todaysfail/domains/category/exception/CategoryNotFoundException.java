package com.todaysfail.domains.category.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class CategoryNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new CategoryNotFoundException();

    public CategoryNotFoundException() {
        super(CategoryErrorCode.CATEGORY_NOT_FOUND);
    }
}
