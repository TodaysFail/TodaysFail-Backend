package com.todaysfail.events.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.todaysfail.common.annotation.EventHandler;
import com.todaysfail.events.UserRegisterEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EventHandler
@RequiredArgsConstructor
public class UserRegisterEventHandler {
	@Async
	@TransactionalEventListener(classes = UserRegisterEvent.class, phase = TransactionPhase.AFTER_COMMIT)
	public void handleUserRegisterEvent(UserRegisterEvent userRegisterEvent) {
		Long userId = userRegisterEvent.getUserId();
		log.info("[DOMAIN EVENT : UserRegisterEvent] userId: {}", userId);
	}
}
