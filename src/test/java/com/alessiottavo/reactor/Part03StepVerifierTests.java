package com.alessiottavo.reactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import com.alessiottavo.reactor.Repository.ReactiveUserRepository;

import reactor.test.StepVerifier;

public class Part03StepVerifierTests {
	
	Part03StepVerifier testee = new Part03StepVerifier();
	ReactiveUserRepository userRepository = new ReactiveUserRepository(1);
	
	@Test
	void expectSkylerJesseComplete() {
		
		StepVerifier.create(testee.skylerJesseComplete())
		.expectNext(userRepository.findById("swhite").block())
		.expectNext(userRepository.findById("jpinkman").block())
		.verifyComplete();
	}
	
	@Test
	void expect3600Elements() {
		
		StepVerifier.withVirtualTime(() -> testee.flux3600Elements())
		.expectSubscription()
		.thenAwait(Duration.ofHours(1))
		.expectNextCount(3600)
		.verifyComplete();
	}
}
