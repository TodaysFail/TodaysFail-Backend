package com.todaysfail.domains.category.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class CategoryNotOwnedByUserException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new CategoryNotOwnedByUserException();

    public CategoryNotOwnedByUserException() {
        super(CategoryErrorCode.CATEGORY_NOT_OWNED_BY_USER);
    }
}
