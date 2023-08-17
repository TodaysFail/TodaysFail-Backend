package com.todaysfail.events;

import com.todaysfail.aop.event.DomainEvent;
import lombok.Getter;

@Getter
public class UserRegisterEvent extends DomainEvent {
    private final Long userId;

    public UserRegisterEvent(Long userId) {
        this.userId = userId;
    }
}
