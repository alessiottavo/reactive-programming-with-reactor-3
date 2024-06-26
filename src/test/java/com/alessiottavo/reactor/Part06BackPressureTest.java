package com.alessiottavo.reactor;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;
import com.alessiottavo.reactor.Repository.Entity.User;

import reactor.test.StepVerifier;

public class Part06BackPressureTest {

	ReactiveUserRepository repository = new ReactiveUserRepository(10L);

	@Test
	void requestAllExpectFour() {

		StepVerifier.create(repository.findAll()).thenRequest(Long.MAX_VALUE).expectNextCount(4L).expectComplete()
				.verify();
	}

	@Test
	void requestSkylerThenJessie() {

		StepVerifier.create(repository.findAll()).thenRequest(1L).expectNext(User.SKYLER).thenRequest(1L)
				.expectNext(User.JESSE).thenCancel().verify();
	}
}
