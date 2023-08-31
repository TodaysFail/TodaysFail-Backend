package com.todaysfail.domains.tag.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class TagCountExceedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new TagCountExceedException();

    public TagCountExceedException() {
        super(TagErrorCode.FAILURE_TAG_COUNT_EXCEED);
    }
}
