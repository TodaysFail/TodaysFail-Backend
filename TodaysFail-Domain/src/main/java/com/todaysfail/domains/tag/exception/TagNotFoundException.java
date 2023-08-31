package com.todaysfail.domains.tag.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class TagNotFoundException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new TagNotFoundException();

    public TagNotFoundException() {
        super(TagErrorCode.TAG_NOT_FOUND);
    }
}
