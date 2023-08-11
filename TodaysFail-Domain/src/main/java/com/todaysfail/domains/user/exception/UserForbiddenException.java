package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class UserForbiddenException extends TodaysFailCodeException {
	public static final TodaysFailCodeException EXCEPTION = new UserForbiddenException();

	public UserForbiddenException() {
		super(UserErrorCode.NICKNAME_GENERATION_FAILED);
	}
}