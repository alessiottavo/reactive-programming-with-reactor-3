package com.alessiottavo.reactive_programming_with_reactor_3;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.test.StepVerifier;

public class Part02MonoTests {
	
	Part02Mono testee = new Part02Mono();
	
	@Test
	void expectEmpty() {
		
		StepVerifier.create(testee.emptyMono())
		.verifyComplete();
	}
	
	@Test
	void expectNoSig() {
		
		StepVerifier.create(testee.monoWithNoSignal())
		.expectSubscription()
		.verifyTimeout(Duration.ofMillis(1000L));
	}
	
	@Test
	void expectFoo() {
		
		StepVerifier.create(testee.fooMono())
		.expectNext("foo")
		.verifyComplete();
	}
	
	@Test
	void expectError() {
		
		StepVerifier.create(testee.errorMono())
		.expectError()
		.verify();
	}

}
