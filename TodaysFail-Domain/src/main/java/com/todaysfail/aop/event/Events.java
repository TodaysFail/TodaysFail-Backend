package com.todaysfail.aop.event;

import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class Events {

    private static ThreadLocal<ApplicationEventPublisher> publisherLocal = new ThreadLocal<>();

    public static void raise(DomainEvent event) {
        if (event == null) return;

        if (publisherLocal.get() != null) {
            publisherLocal.get().publishEvent(event);
        }
    }

    public static void setPublisher(ApplicationEventPublisher publisher) {
        publisherLocal.set(publisher);
    }

    public static void reset() {
        publisherLocal.remove();
    }
}
