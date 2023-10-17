package com.todaysfail.domains.tag.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class TagNameLengthExceedException extends TodaysFailCodeException {
    public static final TodaysFailCodeException EXCEPTION = new TagNameLengthExceedException();

    public TagNameLengthExceedException() {
        super(TagErrorCode.TAG_NAME_LENGTH_EXCEED);
    }
}
