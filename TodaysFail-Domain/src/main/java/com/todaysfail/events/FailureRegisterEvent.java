package com.todaysfail.events;

import com.todaysfail.aop.event.DomainEvent;
import com.todaysfail.domains.failure.domain.Failure;
import lombok.Getter;

@Getter
public class FailureRegisterEvent extends DomainEvent {
    private final Failure failure;

    public FailureRegisterEvent(Failure failure) {
        this.failure = failure;
    }
}
