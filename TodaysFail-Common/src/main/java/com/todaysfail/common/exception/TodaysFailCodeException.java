package com.todaysfail.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TodaysFailCodeException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
