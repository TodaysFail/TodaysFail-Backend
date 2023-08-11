package com.todaysfail.domains.user.exception;

import com.todaysfail.common.exception.TodaysFailCodeException;

public class UserNotFountException extends TodaysFailCodeException {
	public static final TodaysFailCodeException EXCEPTION = new UserNotFountException();

	public UserNotFountException() {
		super(UserErrorCode.USER_NOT_FOUND);
	}
}
